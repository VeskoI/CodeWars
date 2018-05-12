package com.veskoiliev.assertion;

import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class ViewAssertions {

    public static void assertDisplayingView(Matcher<View> viewMatcher) {
        onView(viewMatcher).check(matches(isDisplayed()));
    }

    public static void assertNotDisplayingView(Matcher<View> viewMatcher) {
        onView(isRoot()).check(matches(not(hasDescendant(allOf(viewMatcher, isDisplayed())))));
    }
}
