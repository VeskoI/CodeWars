package com.veskoiliev.codewars.data.local.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.veskoiliev.codewars.data.NetworkState
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/**
 * This boundary callback gets notified when user reaches to the edges of the list such that the
 * database cannot provide any more data.
 * <p>
 * NOTE: The boundary callback might be called multiple times for the same direction so it does its own
 * rate limiting using the PagingRequestHelper class.
 */
class CompletedChallengeBoundaryCallback(
        private val userName: String,
        private val restApi: CodeWarsRestApi,
        private val workerThread: Scheduler,
        private val observeThread: Scheduler,
        private val handleSuccessfulResponse: (String, List<CompletedChallenge>) -> Unit
) : PagedList.BoundaryCallback<CompletedChallenge>() {

    private val networkState = MutableLiveData<NetworkState>()
    private var initialLoadingDisposable: Disposable? = null
    private var loadingMoreDisposable: Disposable? = null
    private var currentPage = 0

    fun networkState(): LiveData<NetworkState> = networkState

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        if (!initialCallRunning()) {
            initialLoadingDisposable = loadData(currentPage++)
        }
    }

    /**
     * User reached to the end of the list.
     */
    override fun onItemAtEndLoaded(itemAtEnd: CompletedChallenge) {
        if (!loadingMoreData()) {
            loadingMoreDisposable = loadData(currentPage++)
        }
    }

    private fun initialCallRunning() = initialLoadingDisposable != null && initialLoadingDisposable?.isDisposed == false

    private fun loadingMoreData() = loadingMoreDisposable != null && loadingMoreDisposable?.isDisposed == false

    private fun loadData(page: Int): Disposable? {
        return restApi.getCompletedChallenges(userName, page = page)
                .subscribeOn(workerThread)
                .doOnSubscribe { networkState.value = NetworkState.LOADING }
                .flatMap { challenges -> Single.fromCallable { handleSuccessfulResponse(userName, challenges) } }
                .observeOn(observeThread)
                .subscribe(
                        { _ -> networkState.value = NetworkState.LOADED },
                        { throwable -> networkState.value = NetworkState.error(throwable?.message) }
                )
    }
}