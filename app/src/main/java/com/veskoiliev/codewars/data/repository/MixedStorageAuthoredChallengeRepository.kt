package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import io.reactivex.Single
import javax.inject.Inject

class MixedStorageAuthoredChallengeRepository @Inject constructor(

) : AuthoredChallengeRepository {

    override fun authoredChallenges(userName: String): Single<List<AuthoredChallenge>> {
        // TODO not implemented
        return Single.just(listOf(
                AuthoredChallenge(id = "id1", name = "name1", description = "desc1", userName = "Voile"),
                AuthoredChallenge(id = "id2", name = "name2", description = "desc2", userName = "Voile")
        ))
    }
}