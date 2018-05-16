package com.veskoiliev.codewars.data.remote.mapper

import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.model.UserModel
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun map(networkModel: UserModel): User {
        val bestLanguage = getBestLanguage(networkModel)

        return User(
                username = networkModel.username,
                name = if (networkModel.name.isNotEmpty()) networkModel.name else networkModel.username,
                rank = networkModel.ranks.overallRank.rank,
                leaderBoardPosition = networkModel.leaderBoardPosition,
                bestLanguage = bestLanguage.name,
                bestLanguagePoints = bestLanguage.points
        )
    }

    private fun getBestLanguage(networkModel: UserModel): BestLanguage {
        val bestEntry = networkModel.ranks.languages.maxBy { (_, value) -> value.score }
        return BestLanguage(name = bestEntry?.key, points = bestEntry?.value?.score)
    }
}

private data class BestLanguage(val name: String?, val points: Long?)
