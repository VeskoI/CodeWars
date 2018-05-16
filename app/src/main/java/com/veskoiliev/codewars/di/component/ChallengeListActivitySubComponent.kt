package com.veskoiliev.codewars.di.component

import com.veskoiliev.codewars.ui.challengelist.ChallengeListActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent()
interface ChallengeListActivitySubComponent : AndroidInjector<ChallengeListActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ChallengeListActivity>()
}