package com.veskoiliev.codewars.data.remote

import com.veskoiliev.codewars.data.remote.model.UserModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

private const val PARAM_USERNAME = "userName"
private const val PATH_GET_USER = "v1/users/{$PARAM_USERNAME}"

interface CodeWarsApiService {

    @GET(PATH_GET_USER)
    fun getUser(@Path(PARAM_USERNAME) userName: String): Single<UserModel>
}