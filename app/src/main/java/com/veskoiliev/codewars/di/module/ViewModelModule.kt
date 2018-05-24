package com.veskoiliev.codewars.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.veskoiliev.codewars.di.util.ViewModelFactory
import com.veskoiliev.codewars.di.util.ViewModelKey
import com.veskoiliev.codewars.ui.challengelist.authored.AuthoredChallengesViewModel
import com.veskoiliev.codewars.ui.challengelist.completed.CompletedChallengesViewModel
import com.veskoiliev.codewars.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompletedChallengesViewModel::class)
    internal abstract fun bindCompletedChallengesViewModel(completedChallengesViewModel: CompletedChallengesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthoredChallengesViewModel::class)
    internal abstract fun bindAuthoredChallengesViewModel(authoredChallengesViewModel: AuthoredChallengesViewModel): ViewModel
}