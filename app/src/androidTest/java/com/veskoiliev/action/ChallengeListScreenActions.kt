package com.veskoiliev.action

import android.support.test.espresso.matcher.ViewMatchers.withId
import com.veskoiliev.action.ViewActions.selectView
import com.veskoiliev.codewars.R

class ChallengeListScreenActions {

    fun selectsAuthoredTab() {
        selectView(withId(R.id.authoredChallengesFragment))
    }
}