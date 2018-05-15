package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
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

    private val subscriptions = CompositeDisposable()
    private val searchedUser = MutableLiveData<Resource<User>>()

    fun searchedUser(): LiveData<Resource<User>> {
        return searchedUser
    }

    fun searchHistory(): LiveData<List<User>> {
        return userRepository.getSearchHistory()
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
}