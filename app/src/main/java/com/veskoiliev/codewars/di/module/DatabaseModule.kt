package com.veskoiliev.codewars.di.module

import android.arch.persistence.room.Room
import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.data.local.db.AuthoredChallengeDao
import com.veskoiliev.codewars.data.local.db.Database
import com.veskoiliev.codewars.data.local.db.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    private val databaseName = "codewars.db"

    @Singleton
    @Provides
    fun provideUserDao(database: Database): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideAuthoredChallengesDao(database: Database): AuthoredChallengeDao {
        return database.authoredChallengeDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: CodeWarsApplication): Database {
        return Room.databaseBuilder(application, Database::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
    }
}