package com.veskoiliev.codewars.data.local.model.challenge

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.veskoiliev.codewars.data.local.model.User

@Entity(foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["username"], childColumns = ["userName"], onDelete = CASCADE)
])
data class AuthoredChallenge(
        @PrimaryKey val id: String,
        val userName: String,
        val name: String,
        val description: String
)