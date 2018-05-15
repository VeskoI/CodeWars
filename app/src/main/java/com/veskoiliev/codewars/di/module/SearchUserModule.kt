package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.ui.search.SearchUserActivity
import com.veskoiliev.codewars.ui.search.SearchUserView
import dagger.Module
import dagger.Provides

@Module
class SearchUserModule {

    @Provides
    fun provideSearchUserView(searchUserActivity: SearchUserActivity): SearchUserView {
        return searchUserActivity
    }
}