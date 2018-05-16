package com.veskoiliev.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher

object RecyclerViewAssertions {

    fun assertRecyclerViewItemCount(@IdRes recyclerViewId: Int, itemCount: Int) {
        onView(withId(recyclerViewId)).check(matches(hasItemCount(itemCount)))
    }

    /**
     * Match the first RecyclerView items against the given itemMatchersList elements.
     * If itemMatchersList.size == RecyclerView.childCount, then all elements of the RecyclerView will be matched.
     *
     * @param recyclerViewId the id of the RecyclerView to assert on
     * @param itemMatchersList ordered list of Matchers to be matched against the corresponding child of the Recycler view
     * (e.g. 0th matcher against 0th child, 1st -> 1st, etc).
     */
    fun assertRecyclerViewItemsInOrder(@IdRes recyclerViewId: Int, itemMatchersList: List<Matcher<View>>) {
        itemMatchersList.forEachIndexed({ position, itemMatcher ->
            onView(withId(recyclerViewId)).check(matches(atPosition(position, itemMatcher)))
        })
    }

    private fun hasItemCount(expectedCount: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has $expectedCount items")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return recyclerView.adapter != null && recyclerView.adapter.itemCount == expectedCount
            }
        }
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                        ?: return false // has no item on such position
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}
