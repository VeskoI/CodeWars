package com.veskoiliev.codewars.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.veskoiliev.codewars.data.local.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}