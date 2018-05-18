package com.veskoiliev.di

import com.veskoiliev.BaseInstrumentationTestCase
import com.veskoiliev.Given
import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.di.component.AppComponent
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
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(app: CodeWarsApplication): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): TestAppComponent
    }

    fun inject(instrumentationTestCase: BaseInstrumentationTestCase)

    fun inject(given: Given)
}