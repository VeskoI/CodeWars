package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.local.db.AuthoredChallengeDao
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.di.component.NamedParams
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class MixedStorageAuthoredChallengeRepository @Inject constructor(
        private val challengesDao: AuthoredChallengeDao,
        private val restApi: CodeWarsRestApi,
        @Named(NamedParams.RX_WORKER_THREAD) private val workerThread: Scheduler
) : AuthoredChallengeRepository {

    override fun authoredChallenges(userName: String): Single<List<AuthoredChallenge>> {
        return challengesDao.getAuthoredChallenges(userName)
                .subscribeOn(workerThread)
                .onErrorResumeNext(Single.defer { fetchAndSaveChallenges(userName) })
                .filter { it.isNotEmpty() }
                .switchIfEmpty(Single.defer { fetchAndSaveChallenges(userName) })
    }

    private fun fetchAndSaveChallenges(userName: String): Single<List<AuthoredChallenge>> {
        return restApi.getAuthoredChallenges(userName)
                .doOnSuccess({ challengesDao.save(it) })
    }
}