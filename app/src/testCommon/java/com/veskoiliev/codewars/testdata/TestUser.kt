package com.veskoiliev.codewars.testdata

import com.veskoiliev.codewars.data.local.model.User

object TestUser {

    val user = User(username = "username", name = "name", rank = 100, leaderBoardPosition = 3, bestLanguage = "Kotlin", bestLanguagePoints = 3432)
    val user2 = User(username = "username2", name = "name2", rank = 1, leaderBoardPosition = 2, bestLanguage = "Kotlin", bestLanguagePoints = 3432)
    val user3 = User(username = "username3", name = "name3", rank = 10, leaderBoardPosition = 1, bestLanguage = "Kotlin", bestLanguagePoints = 3432)

    val usersList = listOf(user, user2, user3)
    val usersListSortedByRank = listOf(user, user3, user2)
    val usersListSortedByLeaderBoardPosition = listOf(user3, user2, user)
}