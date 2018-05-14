package com.veskoiliev.action

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object ViewActions {

    fun typeText(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(ViewActions.typeText(text))
    }

    fun selectView(viewMatcher: Matcher<View>) {
        onView(allOf(isDisplayed(), viewMatcher)).perform(click())
    }
}