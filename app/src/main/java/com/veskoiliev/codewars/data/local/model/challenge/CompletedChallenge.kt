package com.veskoiliev.codewars.data.local.model.challenge

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class CompletedChallenge(
        @PrimaryKey
        val id: Long,
        val name: String,
        val completedAt: String
)