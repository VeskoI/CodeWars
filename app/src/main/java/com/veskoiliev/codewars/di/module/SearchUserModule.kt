package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.ui.search.SearchUserActivity
import com.veskoiliev.codewars.ui.search.SearchUserView
import dagger.Binds
import dagger.Module

@Module
abstract class SearchUserModule {

    @Binds
    abstract fun provideSearchUserView(searchUserActivity: SearchUserActivity): SearchUserView
}