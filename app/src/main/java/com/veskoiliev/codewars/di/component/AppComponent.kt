package com.veskoiliev.codewars.di.component

import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    RxModule::class,
    DatabaseModule::class,
    NetworkModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun app(app: CodeWarsApplication): Builder
    }

    fun inject(app: CodeWarsApplication)
}