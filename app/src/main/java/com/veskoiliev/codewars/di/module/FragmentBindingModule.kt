package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.ui.challengelist.CompletedChallengesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun completedChallengesFragment(): CompletedChallengesFragment
}