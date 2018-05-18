package com.veskoiliev.codewars.data.remote

import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.mapper.ChallengesMapper
import com.veskoiliev.codewars.data.remote.mapper.UserMapper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CodeWarsRestApi @Inject constructor(private val service: CodeWarsApiService,
                                          private val userMapper: UserMapper,
                                          private val challengesMapper: ChallengesMapper) {

    fun getUser(userName: String): Single<User> {
        return service.getUser(userName)
                .map { userMapper.map(it) }
    }

    fun getCompletedChallenges(userName: String, page: Int): Single<List<CompletedChallenge>> {
        return service.getCompletedChallenges(userName, page)
                .map { challengesMapper.mapCompletedChallenges(it, page) }
    }
}