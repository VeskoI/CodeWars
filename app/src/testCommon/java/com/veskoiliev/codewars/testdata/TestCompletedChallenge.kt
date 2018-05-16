package com.veskoiliev.codewars.testdata

import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengeModel
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengesResponseModel

object TestCompletedChallenge {

    val completedChallengesResponseModel = CompletedChallengesResponseModel(
            totalPages = 2,
            totalItems = 232,
            data = listOf(
                    CompletedChallengeModel(1, "name1", "completedAt1"),
                    CompletedChallengeModel(2, "name2", "completedAt2"),
                    CompletedChallengeModel(3, "name3", "completedAt3")
            )
    )

    val completedChallengesList = listOf(
            CompletedChallenge(1, "name1", "completedAt1"),
            CompletedChallenge(2, "name2", "completedAt2"),
            CompletedChallenge(3, "name3", "completedAt3")
    )
}