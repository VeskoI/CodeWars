package com.veskoiliev.action

import android.support.test.rule.ActivityTestRule
import com.veskoiliev.codewars.ui.search.SearchUserActivity

class UserActions(private val activityRule: ActivityTestRule<SearchUserActivity>) {

    val onSearchScreen = SearchScreenActions()
    val onChallengeScreen = ChallengeListScreenActions()

    fun launchesTheApp() {
        activityRule.launchActivity(null)
    }
}