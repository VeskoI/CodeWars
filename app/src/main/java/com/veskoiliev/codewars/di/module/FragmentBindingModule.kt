package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.ui.challengelist.authored.AuthoredChallengesFragment
import com.veskoiliev.codewars.ui.challengelist.authored.AuthoredChallengesView
import com.veskoiliev.codewars.ui.challengelist.completed.CompletedChallengesFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun completedChallengesFragment(): CompletedChallengesFragment

    @ContributesAndroidInjector
    abstract fun authoredChallengesFragment(): AuthoredChallengesFragment

    @Binds
    abstract fun provideAuthoredChallengesView(authoredChallengesFragment: AuthoredChallengesFragment): AuthoredChallengesView
}