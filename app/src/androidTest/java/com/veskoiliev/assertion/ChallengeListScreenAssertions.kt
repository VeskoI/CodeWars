package com.veskoiliev.assertion

import android.support.test.espresso.matcher.ViewMatchers.withId
import com.veskoiliev.assertion.ViewAssertions.assertDisplayingView
import com.veskoiliev.codewars.R

class ChallengeListScreenAssertions {

    init {
        assertDisplayingView(withId(R.id.challenge_list_bottom_navigation))
    }
}
