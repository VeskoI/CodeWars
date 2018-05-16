package com.veskoiliev

import android.support.test.InstrumentationRegistry
import com.veskoiliev.arrangements.CodeWarsServer
import com.veskoiliev.arrangements.DatabaseArrangements
import com.veskoiliev.rule.mockwebserver.MockWebServerRule
import javax.inject.Inject

class Given(serverRule: MockWebServerRule) {
    @Inject
    lateinit var database: DatabaseArrangements

    val codeWarsServer: CodeWarsServer = CodeWarsServer(serverRule)

    init {
        val testApp = InstrumentationRegistry.getTargetContext().applicationContext as TestCodeWarsApplication
        testApp.testAppComponent.inject(this)
    }
}