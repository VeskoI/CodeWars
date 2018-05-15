package com.veskoiliev.codewars.testdata

import com.veskoiliev.codewars.data.local.model.User

object TestUser {

    val user = User(username = "username", name = "name", rank = 123, bestLanguage = "Kotlin", bestLanguagePoints = 3432)

    val usersList = listOf(
            user,
            user.copy(username = "username2", name = "name2"),
            user.copy(username = "username3", name = "name3")
    )
}