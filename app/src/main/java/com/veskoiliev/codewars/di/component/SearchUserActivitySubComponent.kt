package com.veskoiliev.codewars.di.component

import com.veskoiliev.codewars.di.module.SearchUserModule
import com.veskoiliev.codewars.ui.search.SearchUserActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [SearchUserModule::class])
interface SearchUserActivitySubComponent : AndroidInjector<SearchUserActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SearchUserActivity>()
}