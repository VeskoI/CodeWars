package com.veskoiliev.codewars.data.repository

import android.arch.lifecycle.LiveData
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import io.reactivex.Single

interface UserRepository {

    fun getUser(userName: String): Single<User>

    fun getSearchHistory(sortOption: SortOption): LiveData<List<User>>
}