package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.Listing
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge

interface ChallengeRepository {

    fun completedChallenges(userName: String, uiPageSize: Int): Listing<CompletedChallenge>
}