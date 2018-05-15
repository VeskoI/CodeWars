package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.repository.UserRepository
import com.veskoiliev.codewars.di.component.NamedParams
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel @Inject constructor(
        private val userRepository: UserRepository,
        @Named(NamedParams.RX_OBSERVE_THREAD) private val observeThread: Scheduler
) : ViewModel() {

    var searchHistorySortOption = SortOption.SEARCH_TIME
        set(value) {
            field = value
            refreshSearchHistorySortOption(value)
        }

    private lateinit var searchHistoryLiveData: LiveData<List<User>>
    private val mediatorSearchHistoryLiveData = MediatorLiveData<List<User>>()
    private val subscriptions = CompositeDisposable()
    private val searchedUser = MutableLiveData<Resource<User>>()

    fun searchedUser(): LiveData<Resource<User>> {
        return searchedUser
    }

    fun searchHistory(): LiveData<List<User>> {
        searchHistoryLiveData = userRepository.getSearchHistory(searchHistorySortOption)
        wrapSearchHistoryLiveDataWithMediator()
        return mediatorSearchHistoryLiveData
    }

    fun onSearchClicked(userName: String) {
        subscriptions.add(
                userRepository.getUser(userName)
                        .doOnSubscribe { updateSearchedUser(Resource.LoadingResource()) }
                        .observeOn(observeThread)
                        .subscribe(
                                { updateSearchedUser(Resource.SuccessResource(it)) },
                                { updateSearchedUser(Resource.ErrorResource(R.string.error_user_not_found)) }
                        ))
    }

    private fun updateSearchedUser(resource: Resource<User>) {
        searchedUser.value = resource
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    private fun refreshSearchHistorySortOption(newSortOption: SortOption) {
        mediatorSearchHistoryLiveData.removeSource(searchHistoryLiveData)
        searchHistoryLiveData = userRepository.getSearchHistory(newSortOption)
        wrapSearchHistoryLiveDataWithMediator()
    }

    private fun wrapSearchHistoryLiveDataWithMediator() {
        mediatorSearchHistoryLiveData.addSource(searchHistoryLiveData, {
            mediatorSearchHistoryLiveData.value = it
        })
    }
}
