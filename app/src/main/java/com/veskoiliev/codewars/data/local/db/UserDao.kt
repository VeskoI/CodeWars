package com.veskoiliev.codewars.data.local.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.veskoiliev.codewars.data.local.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUser(username: String): User

    @Query("SELECT * FROM user")
    fun getSearchHistory(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}