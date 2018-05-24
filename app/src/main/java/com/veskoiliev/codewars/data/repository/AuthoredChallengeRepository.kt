package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import io.reactivex.Single

interface AuthoredChallengeRepository {

    fun authoredChallenges(userName: String): Single<List<AuthoredChallenge>>
}