package com.veskoiliev.codewars.di.module

import android.app.Activity
import com.veskoiliev.codewars.di.component.ChallengeListActivitySubComponent
import com.veskoiliev.codewars.di.component.SearchUserActivitySubComponent
import com.veskoiliev.codewars.ui.challengelist.ChallengeListActivity
import com.veskoiliev.codewars.ui.search.SearchUserActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    SearchUserActivitySubComponent::class,
    ChallengeListActivitySubComponent::class
])
abstract class ActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(SearchUserActivity::class)
    internal abstract fun bindSearchUserActivity(builder: SearchUserActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(ChallengeListActivity::class)
    internal abstract fun bindChallengeListActivity(builder: ChallengeListActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}