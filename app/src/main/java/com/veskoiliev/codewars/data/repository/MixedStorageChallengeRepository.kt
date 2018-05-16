package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.Listing
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import javax.inject.Inject

class MixedStorageChallengeRepository @Inject constructor() : ChallengeRepository {

    override fun completedChallenges(userName: String, uiPageSize: Int): Listing<CompletedChallenge> {
        // TODO not implemented
        throw IllegalStateException("not implemented")
    }
}