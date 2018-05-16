package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.data.repository.ChallengeRepository
import com.veskoiliev.codewars.data.repository.MixedStorageChallengeRepository
import com.veskoiliev.codewars.data.repository.MixedStorageUserRepository
import com.veskoiliev.codewars.data.repository.UserRepository
import com.veskoiliev.codewars.domain.SystemTimeProvider
import com.veskoiliev.codewars.domain.TimeProvider
import dagger.Binds
import dagger.Module

@Module
abstract class BindingsModule {

    @Binds
    abstract fun provideUserRepository(mixedStorageUserRepository: MixedStorageUserRepository): UserRepository

    @Binds
    abstract fun provideChallengeRepository(mixedStorageChallengeRepository: MixedStorageChallengeRepository): ChallengeRepository

    @Binds
    abstract fun provideTimeProvider(systemTimeProvider: SystemTimeProvider): TimeProvider
}