package com.veskoiliev

import com.veskoiliev.codewars.CodeWarsApplication
import com.veskoiliev.codewars.di.module.NetworkModule
import com.veskoiliev.rule.mockwebserver.MockWebServerRule

class TestCodeWarsApplication : CodeWarsApplication() {

    override fun getNetworkModule(): NetworkModule {
        return NetworkModule(MockWebServerRule.BASE_URL)
    }
}