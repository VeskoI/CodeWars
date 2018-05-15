package com.veskoiliev.codewars.data.repository

import android.arch.lifecycle.LiveData
import com.veskoiliev.codewars.data.local.db.UserDao
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import io.reactivex.Single
import javax.inject.Inject

class MixedStorageUserRepository @Inject constructor(
        private val userDao: UserDao,
        private val restApi: CodeWarsRestApi
): UserRepository {
    override fun getUser(username: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSearchHistory(sortOption: SortOption): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}