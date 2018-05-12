package com.veskoiliev.assertion

import android.support.test.espresso.matcher.ViewMatchers.withText
import com.veskoiliev.assertion.ViewAssertions.assertDisplayingView
import com.veskoiliev.codewars.R

/**
 * Wrapper for various screen assertions.
 *
 * Typical usage is:
 * _then.user.sees.someScreenIsDisplayed()_
 */
class ScreenAssertion {

    fun searchUserScreen() {
        assertDisplayingView(withText(R.string.app_name))
    }
}
