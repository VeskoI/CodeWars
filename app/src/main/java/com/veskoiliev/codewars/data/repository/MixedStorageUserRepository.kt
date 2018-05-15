package com.veskoiliev.codewars.data.repository

import android.arch.lifecycle.LiveData
import com.veskoiliev.codewars.data.local.db.UserDao
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.di.component.NamedParams
import com.veskoiliev.codewars.domain.TimeProvider
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import javax.inject.Inject
import javax.inject.Named

class MixedStorageUserRepository @Inject constructor(
        private val userDao: UserDao,
        private val restApi: CodeWarsRestApi,
        private val timeProvider: TimeProvider,
        @Named(NamedParams.RX_WORKER_THREAD) private val workerThread: Scheduler
): UserRepository {
    override fun getUser(userName: String): Single<User> {
        return userDao.getUser(userName)
                .onErrorResumeNext { fetchAndSaveUserFromNetwork(userName) }
                .subscribeOn(workerThread)
    }

    private fun fetchAndSaveUserFromNetwork(userName: String): SingleSource<out User> {
        return restApi.getUser(userName)
                .doOnSuccess { userDao.saveUser(it.copy(searchedTimestamp = timeProvider.currentTimeMillis())) }
                .subscribeOn(workerThread)
    }

    override fun getSearchHistory(sortOption: SortOption): LiveData<List<User>> = when(sortOption) {
        SortOption.SEARCH_TIME -> userDao.getSearchHistoryBySearchTime()
        SortOption.RANK -> userDao.getSearchHistoryByRank()
        SortOption.LEADER_BOARD_POSITION -> userDao.getSearchHistoryByLeaderBoardPosition()
    }
}