package com.veskoiliev.assertion

import android.support.test.espresso.matcher.ViewMatchers.withText
import com.veskoiliev.assertion.ViewAssertions.assertDisplayingView
import com.veskoiliev.codewars.R

class SearchScreenAssertions {

    init {
        assertDisplayingView(withText(R.string.app_name))
    }

    fun withUserNotFoundError() {
        assertDisplayingView(withText(R.string.error_user_not_found))
    }
}