package com.veskoiliev.codewars.data.remote.mapper

import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengesResponseModel
import javax.inject.Inject

class ChallengesMapper @Inject constructor() {

    fun mapCompletedChallenges(response: CompletedChallengesResponseModel): List<CompletedChallenge> =
            response.data.map {
                CompletedChallenge(id = it.id, name = it.name, completedAt = it.completedAt)
            }
}