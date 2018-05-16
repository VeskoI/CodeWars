package com.veskoiliev

import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.di.module.NetworkModule
import com.veskoiliev.di.DaggerTestAppComponent
import com.veskoiliev.di.TestAppComponent
import com.veskoiliev.rule.mockwebserver.MockWebServerRule

class TestCodeWarsApplication : CodeWarsApplication() {

    val testAppComponent: TestAppComponent by lazy {
        DaggerTestAppComponent.builder()
                .networkModule(NetworkModule(MockWebServerRule.BASE_URL))
                .app(this)
                .build()
    }

    override fun initAppComponent() = testAppComponent
}