package com.veskoiliev.codewars.testdata

import com.veskoiliev.codewars.data.remote.model.RankModel
import com.veskoiliev.codewars.data.remote.model.RanksModel
import com.veskoiliev.codewars.data.remote.model.UserModel

object TestUserModel {

    const val userName = "viper_33"
    const val name = "AwesomeName"
    const val overallRank: Long = 7L
    const val leaderBoardPosition = 312L
    const val bestLanguageName = "kotlin"
    const val bestLanguageScore = 9999L

    private val languagesMap: Map<String, RankModel> = mapOf(
            "java" to RankModel(rank = 1231, score = 1),
            bestLanguageName to RankModel(rank = 12, score = bestLanguageScore),
            "c++" to RankModel(rank = 324, score = 100)
    )

    private val ranksModel = RanksModel(
            overallRank = RankModel(rank = overallRank),
            languages = languagesMap
    )

    val userModel = UserModel(
            username = userName,
            name = name,
            leaderBoardPosition = leaderBoardPosition,
            ranks = ranksModel
    )

    val userModelWithoutAnyLanguages = userModel.copy(
            ranks = RanksModel(
                    overallRank = RankModel(rank = overallRank),
                    languages = emptyMap()
            )
    )
}