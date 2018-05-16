package com.veskoiliev.codewars.data.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import com.veskoiliev.codewars.data.Listing
import com.veskoiliev.codewars.data.NetworkState
import com.veskoiliev.codewars.data.local.db.CompletedChallengeBoundaryCallback
import com.veskoiliev.codewars.data.local.db.Database
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.local.model.challenge.UserCompletedChallengeJoin
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.di.component.NamedParams
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class MixedStorageChallengeRepository @Inject constructor(
        private val restApi: CodeWarsRestApi,
        private val database: Database,
        @Named(NamedParams.RX_WORKER_THREAD) private val workerThread: Scheduler,
        @Named(NamedParams.RX_OBSERVE_THREAD) private val observeThread: Scheduler
) : ChallengeRepository {

    override fun completedChallenges(userName: String, uiPageSize: Int): Listing<CompletedChallenge> {
        val boundaryCallback = CompletedChallengeBoundaryCallback(
                userName = userName,
                restApi = restApi,
                workerThread = workerThread,
                observeThread = observeThread,
                handleSuccessfulResponse = this::insertCompletedChallengesInDb
        )

        val dataSourceFactory = database.completedChallengeDao().getChallengesForUser(userName)
        val builder = LivePagedListBuilder(dataSourceFactory, uiPageSize)
                .setBoundaryCallback(boundaryCallback)

        val refreshState = MutableLiveData<NetworkState>()  // TODO not implemented properly
        return Listing(
                pagedList = builder.build(),
                networkState = boundaryCallback.networkState(),
                refreshState = refreshState,
                retry = {
                    // TODO not implemented
                },
                refresh = {
                    // TODO not implemented
                }
        )
    }

    private fun insertCompletedChallengesInDb(userName: String, completedChallenges: List<CompletedChallenge>) {
        database.runInTransaction {
            database.completedChallengeDao().insertCompletedChallenge(completedChallenges.toTypedArray())
            val userChallengeJoins = completedChallenges.map {
                UserCompletedChallengeJoin(userName, challengeId = it.id)
            }
            database.completedChallengeDao().insertUserCompletedChallengeJoin(userChallengeJoins.toTypedArray())
        }
    }
}