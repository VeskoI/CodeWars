package com.veskoiliev.codewars.data.remote.mapper

import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.model.challenge.AuthoredChallengesResponseModel
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengesResponseModel
import javax.inject.Inject

class ChallengesMapper @Inject constructor() {

    fun mapCompletedChallenges(response: CompletedChallengesResponseModel, page: Int): List<CompletedChallenge> =
            response.data.map {
                CompletedChallenge(id = it.id, name = it.name, completedAt = it.completedAt, networkPage = page)
            }

    fun mapAuthoredChallenges(responseModel: AuthoredChallengesResponseModel, userName: String): List<AuthoredChallenge> =
            responseModel.data.map {
                AuthoredChallenge(id = it.id, name = it.name, description = it.description, userName = userName)
            }
}