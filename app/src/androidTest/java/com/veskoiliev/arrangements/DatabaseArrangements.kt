package com.veskoiliev.arrangements

import com.veskoiliev.codewars.data.local.db.UserDao
import com.veskoiliev.codewars.testdata.TestUser
import javax.inject.Inject

class DatabaseArrangements @Inject constructor(private val userDao: UserDao) {

    fun hasSearchHistoryInDatabase() {
        TestUser.usersList.forEach { userDao.saveUser(it) }
    }
}