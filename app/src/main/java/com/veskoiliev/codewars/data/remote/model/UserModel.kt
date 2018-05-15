package com.veskoiliev.codewars.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Network representation of a User
 */
data class UserModel(
        val username: String?,
        val name: String?,
        val honor: Int?,
        val clan: String?,
        val ranks: RanksModel?,
        @SerializedName("leaderboardPosition") val leaderBoardPosition: Int?
)