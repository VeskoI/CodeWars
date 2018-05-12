package com.veskoiliev

import android.support.test.rule.ActivityTestRule

import com.veskoiliev.action.UserActions
import com.veskoiliev.codewars.SearchUserActivity

class When(activityRule: ActivityTestRule<SearchUserActivity>) {

    val user: UserActions = UserActions(activityRule)
}
