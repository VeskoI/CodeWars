package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.data.repository.MixedStorageUserRepository
import com.veskoiliev.codewars.data.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(mixedStorageUserRepository: MixedStorageUserRepository): UserRepository
}