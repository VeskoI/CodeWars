package com.veskoiliev.codewars.data.repository

import android.arch.lifecycle.LiveData
import com.veskoiliev.codewars.data.local.db.UserDao
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.domain.TimeProvider
import io.reactivex.Single
import io.reactivex.SingleSource
import javax.inject.Inject

class MixedStorageUserRepository @Inject constructor(
        private val userDao: UserDao,
        private val restApi: CodeWarsRestApi,
        private val timeProvider: TimeProvider
): UserRepository {
    override fun getUser(userName: String): Single<User> {
        return userDao.getUser(userName)
                .onErrorResumeNext { fetchAndSaveUserFromNetwork(userName) }
    }

    private fun fetchAndSaveUserFromNetwork(userName: String): SingleSource<out User> {
        return restApi.getUser(userName)
                .doOnSuccess { userDao.saveUser(it.copy(searchedTimestamp = timeProvider.currentTimeMillis())) }
    }

    override fun getSearchHistory(sortOption: SortOption): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}