package com.veskoiliev.codewars.data.local.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.local.model.challenge.UserCompletedChallengeJoin

@Dao
interface CompletedChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompletedChallenge(completedChallenge: Array<CompletedChallenge>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserCompletedChallengeJoin(userCompletedChallengeJoin: Array<UserCompletedChallengeJoin>)

    @Query("SELECT * FROM completedchallenge INNER JOIN usercompletedchallengejoin ON completedchallenge.id = usercompletedchallengejoin.challengeId WHERE usercompletedchallengejoin.userName = :userName")
    fun getChallengesForUser(userName: String): DataSource.Factory<Int, CompletedChallenge>

}