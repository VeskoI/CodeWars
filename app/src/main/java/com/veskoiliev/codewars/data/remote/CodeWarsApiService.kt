package com.veskoiliev.codewars.data.remote

import com.veskoiliev.codewars.data.remote.model.UserModel
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengesResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val PARAM_USERNAME = "userName"
private const val QUERY_PARAM_PAGE = "page"

private const val PATH_GET_USER = "v1/users/{$PARAM_USERNAME}"
private const val PATH_COMPLETED_CHALLENGES = "v1/users/{$PARAM_USERNAME}/code-challenges/completed"

interface CodeWarsApiService {

    @GET(PATH_GET_USER)
    fun getUser(@Path(PARAM_USERNAME) userName: String): Single<UserModel>

    @GET(PATH_COMPLETED_CHALLENGES)
    fun getCompletedChallenges(@Path(PARAM_USERNAME) userName: String, @Query(QUERY_PARAM_PAGE) page: Int = 0): Single<CompletedChallengesResponseModel>
}