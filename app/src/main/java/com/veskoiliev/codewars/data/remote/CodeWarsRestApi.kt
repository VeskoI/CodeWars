package com.veskoiliev.codewars.data.remote

import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.mapper.UserMapper
import io.reactivex.Single
import javax.inject.Inject

class CodeWarsRestApi @Inject constructor(private val service: CodeWarsApiService,
                                          private val userMapper: UserMapper) {

    fun getUser(userName: String): Single<User> {
        return service.getUser(userName)
                .map { userMapper.map(it) }
    }
}