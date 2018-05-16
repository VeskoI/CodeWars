package com.veskoiliev.codewars.data.local.model.challenge

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import com.veskoiliev.codewars.data.local.model.User

/**
 * Helper entity (SQL table) used to implement the M:N relationship between [User] and [CompletedChallenge].
 */
@Entity(
        primaryKeys = ["userName", "challengeId"],
        foreignKeys = [
            ForeignKey(
                    entity = User::class,
                    parentColumns = ["username"],
                    childColumns = ["userName"]
            ),
            ForeignKey(
                    entity = CompletedChallenge::class,
                    parentColumns = ["id"],
                    childColumns = ["challengeId"]
            )
        ]
)
data class UserCompletedChallengeJoin(
        val userName: String,
        val challengeId: Long
)