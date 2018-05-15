package com.veskoiliev.codewars.testdata

import com.veskoiliev.codewars.data.local.model.User

object TestUser {

    val user = User(name = "name", rank = 123, bestLanguage = "Kotlin", bestLanguagePoints = 3432)

    val usersList = listOf(
            user,
            user.copy(name = "name2"),
            user.copy(name = "name3")
    )
}