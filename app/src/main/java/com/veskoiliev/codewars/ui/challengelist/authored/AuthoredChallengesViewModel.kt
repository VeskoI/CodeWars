package com.veskoiliev.codewars.ui.challengelist.authored

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.data.repository.AuthoredChallengeRepository
import com.veskoiliev.codewars.di.component.NamedParams
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class AuthoredChallengesViewModel @Inject constructor(
        private val repository: AuthoredChallengeRepository,
        @Named(NamedParams.RX_OBSERVE_THREAD) private val observeThread: Scheduler
) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val authoredChallenges = MutableLiveData<Resource<List<AuthoredChallenge>>>()
    fun authoredChallenges(): LiveData<Resource<List<AuthoredChallenge>>> = authoredChallenges

    fun init(userName: String) {
        subscriptions.add(
                repository.authoredChallenges(userName)
                        .doOnSubscribe({ updateChallenges(Resource.LoadingResource()) })
                        .observeOn(observeThread)
                        .subscribe(
                                { updateChallenges(Resource.SuccessResource(it)) },
                                { updateChallenges(Resource.ErrorResource(R.string.error_fetching_authored_challenges)) }
                        )
        )
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    private fun updateChallenges(resource: Resource<List<AuthoredChallenge>>) {
        authoredChallenges.value = resource
    }
}