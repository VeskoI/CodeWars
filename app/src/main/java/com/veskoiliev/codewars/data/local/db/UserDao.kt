package com.veskoiliev.codewars.data.local.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import io.reactivex.Single

@Dao
@TypeConverters(SortOptionTypeConverter::class)
interface UserDao {

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUser(username: String): Single<User>

    @Query("SELECT * FROM user ORDER BY :sortOption")
    fun getSearchHistoryOrderedBy(sortOption: SortOption): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)
}