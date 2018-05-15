package com.veskoiliev.codewars.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey
        val username: String,
        val name: String,
        val rank: Long,
        val bestLanguage: String,
        val bestLanguagePoints: Long,
        val searchedTimestamp: Long = 0
)