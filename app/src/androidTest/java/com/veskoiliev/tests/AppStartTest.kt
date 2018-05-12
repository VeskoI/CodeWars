package com.veskoiliev.tests

import com.veskoiliev.BaseInstrumentationTestCase

import org.junit.Test

class AppStartTest : BaseInstrumentationTestCase() {

    @Test
    fun willDisplaySearchUserScreenWhenStartingTheApp() {
        `when`.user.launchesTheApp()

        then.user.sees.searchUserScreen()
    }
}
