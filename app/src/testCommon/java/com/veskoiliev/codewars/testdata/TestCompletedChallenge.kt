package com.veskoiliev.codewars.testdata

import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengeModel
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengesResponseModel

object TestCompletedChallenge {

    val completedChallengesResponseModel = CompletedChallengesResponseModel(
            totalPages = 2,
            totalItems = 232,
            data = listOf(
                    CompletedChallengeModel("id1", "name1", "completedAt1"),
                    CompletedChallengeModel("id2", "name2", "completedAt2"),
                    CompletedChallengeModel("id3", "name3", "completedAt3")
            )
    )

    fun completedChallengesList(page: Int = 0) = listOf(
            CompletedChallenge("id1", "name1", "completedAt1", page),
            CompletedChallenge("id2", "name2", "completedAt2", page),
            CompletedChallenge("id3", "name3", "completedAt3", page)
    )

    val completedChallenge = CompletedChallenge("id1", "name1", "completedAt1", 0)
}