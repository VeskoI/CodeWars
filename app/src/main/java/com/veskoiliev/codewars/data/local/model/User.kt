package com.veskoiliev.codewars.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
        @PrimaryKey
        val username: String,
        val name: String,
        val rank: Long,
        val leaderBoardPosition: Long,
        val bestLanguage: String?,
        val bestLanguagePoints: Long?,
        val searchedTimestamp: Long = 0
) : Parcelable