package com.veskoiliev.codewars.di.component

import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.di.module.ActivityModule
import com.veskoiliev.codewars.di.module.RepositoryModule
import com.veskoiliev.codewars.di.module.RxModule
import com.veskoiliev.codewars.di.module.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    RxModule::class
])
interface AppComponent {

    fun inject(app: CodeWarsApplication)
}