package com.veskoiliev.codewars.di.module

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository() = object : UserRepository {
        private val user = User(name = "name", rank = 123, bestLanguage = "Kotlin", bestLanguagePoints = 3432)
        override fun getUser(username: String): Single<User> {
            // TODO not implemented
            return Single.just(user)
        }

        override fun getSearchHistory(): LiveData<List<User>> {
            // TODO not implemented
            val liveData = MutableLiveData<List<User>>()
            Handler().postDelayed({
                liveData.postValue(listOf(user, user.copy(name = "Name 2"), user.copy(name = "Name 3")))
            }, 5000)

            return liveData
        }
    }
}