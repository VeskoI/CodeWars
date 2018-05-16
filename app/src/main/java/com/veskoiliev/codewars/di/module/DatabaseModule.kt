package com.veskoiliev.codewars.di.module

import android.arch.persistence.room.Room
import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.data.local.db.Database
import com.veskoiliev.codewars.data.local.db.UserDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    private val databaseName = "codewars.db"

    @Provides
    fun provideUserDao(database: Database): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideDatabase(application: CodeWarsApplication): Database {
        return Room.databaseBuilder(application, Database::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
    }
}