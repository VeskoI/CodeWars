package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.local.model.User
import io.reactivex.Single

interface UserRepository {

    fun getUser(username: String): Single<User>
}