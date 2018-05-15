package com.veskoiliev.codewars.data.repository

import android.arch.lifecycle.LiveData
import com.veskoiliev.codewars.data.local.model.User
import io.reactivex.Single

interface UserRepository {

    fun getUser(username: String): Single<User>

    fun getSearchHistory(): LiveData<List<User>>
}