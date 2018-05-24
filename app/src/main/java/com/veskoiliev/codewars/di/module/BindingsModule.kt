package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.data.repository.*
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
    abstract fun provideAuthoredChallengeRepository(mixedStorageAuthoredChallengeRepository: MixedStorageAuthoredChallengeRepository): AuthoredChallengeRepository

    @Binds
    abstract fun provideTimeProvider(systemTimeProvider: SystemTimeProvider): TimeProvider
}