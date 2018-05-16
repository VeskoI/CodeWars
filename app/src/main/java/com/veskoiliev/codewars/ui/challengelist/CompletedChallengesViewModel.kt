package com.veskoiliev.codewars.ui.challengelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.veskoiliev.codewars.data.NetworkState
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.repository.ChallengeRepository
import javax.inject.Inject

class CompletedChallengesViewModel @Inject constructor(
        private val challengeRepository: ChallengeRepository
) : ViewModel() {

    private val userNameLiveData = MutableLiveData<String>()
    private val repositoryChallengeResult = map(userNameLiveData, {
        challengeRepository.completedChallenges(it, COMPLETED_CHALLENGES_UI_PAGE_SIZE)
    })

    val challenges: LiveData<PagedList<CompletedChallenge>> = switchMap(repositoryChallengeResult, { it.pagedList })
    val networkState: LiveData<NetworkState> = switchMap(repositoryChallengeResult, { it.networkState })

    fun init(userName: String) {
        userNameLiveData.value = userName
    }

    fun retry() {
        val listing = repositoryChallengeResult.value
        listing?.retry?.invoke()
    }

    companion object {
        const val COMPLETED_CHALLENGES_UI_PAGE_SIZE = 50
    }
}