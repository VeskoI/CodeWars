package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository() = object : UserRepository {
        override fun getUser(username: String): Single<User> {
            // TODO not implemented
            return Single.just(User(userName = "test", name = "test"))
        }
    }
}