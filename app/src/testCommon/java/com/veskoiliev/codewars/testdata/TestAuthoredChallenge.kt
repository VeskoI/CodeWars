package com.veskoiliev.codewars.testdata

import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.data.remote.model.challenge.AuthoredChallengeModel
import com.veskoiliev.codewars.data.remote.model.challenge.AuthoredChallengesResponseModel

object TestAuthoredChallenge {

    val authoredChallengesResponseModel = AuthoredChallengesResponseModel(data = listOf(
            AuthoredChallengeModel(id = "id1", name = "name1", description = "description1"),
            AuthoredChallengeModel(id = "id2", name = "name2", description = "description2"),
            AuthoredChallengeModel(id = "id3", name = "name3", description = "description3")
    ))

    fun authoredChallengesList(userName: String = "userName") = listOf(
            AuthoredChallenge(id = "id1", name = "name1", description = "description1", userName = userName),
            AuthoredChallenge(id = "id2", name = "name2", description = "description2", userName = userName),
            AuthoredChallenge(id = "id3", name = "name3", description = "description3", userName = userName)
    )
}