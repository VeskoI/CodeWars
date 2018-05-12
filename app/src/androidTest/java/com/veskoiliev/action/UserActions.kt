package com.veskoiliev.action

import android.support.test.rule.ActivityTestRule
import com.veskoiliev.codewars.SearchUserActivity

class UserActions(private val activityRule: ActivityTestRule<SearchUserActivity>) {

    fun launchesTheApp() {
        activityRule.launchActivity(null)
    }
}