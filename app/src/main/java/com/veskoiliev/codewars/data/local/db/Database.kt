package com.veskoiliev.codewars.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.local.model.challenge.UserCompletedChallengeJoin

@Database(
        entities = [User::class, CompletedChallenge::class, UserCompletedChallengeJoin::class],
        version = 1,
        exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun completedChallengeDao(): CompletedChallengeDao
}