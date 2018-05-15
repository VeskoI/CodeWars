package com.veskoiliev.codewars.data.remote.model

import com.google.gson.annotations.SerializedName

data class RanksModel(
        @SerializedName("overall") var overallRank: RankModel,
        var languages: Map<String, RankModel> = emptyMap()
)