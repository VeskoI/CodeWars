package com.veskoiliev.codewars.di.component

import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    BindingsModule::class,
    ViewModelModule::class,
    RxModule::class,
    DatabaseModule::class,
    NetworkModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(app: CodeWarsApplication): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: CodeWarsApplication)
}