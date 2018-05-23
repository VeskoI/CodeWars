package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.ui.search.SearchUserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [SearchUserModule::class])
    abstract fun searchUserActivity(): SearchUserActivity
}