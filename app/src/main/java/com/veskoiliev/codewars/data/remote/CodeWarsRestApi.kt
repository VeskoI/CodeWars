package com.veskoiliev.codewars.data.remote

import com.veskoiliev.codewars.data.local.model.User
import io.reactivex.Single
import javax.inject.Inject

class CodeWarsRestApi @Inject constructor(private val service: CodeWarsApiService) {

    fun getUser(userName: String): Single<User> {
        // TODO not implemented
        return Single.error(Throwable("not implemented"))
    }
}