package com.veskoiliev.codewars.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import io.reactivex.Single

@Dao
interface AuthoredChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(authoredChallenges: List<AuthoredChallenge>)

    @Query("SELECT * FROM authoredchallenge WHERE userName = :userName")
    fun getAuthoredChallenges(userName: String): Single<List<AuthoredChallenge>>
}