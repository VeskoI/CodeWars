package com.veskoiliev.assertion

import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.veskoiliev.assertion.RecyclerViewAssertions.assertRecyclerViewItemCount
import com.veskoiliev.assertion.RecyclerViewAssertions.assertRecyclerViewItemsInOrder
import com.veskoiliev.assertion.ViewAssertions.assertDisplayingView
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.testdata.TestUser
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class SearchScreenAssertions {

    init {
        assertDisplayingView(withText(R.string.app_name))
    }

    fun withUserNotFoundError() {
        assertDisplayingView(withText(R.string.error_user_not_found))
    }

    fun withUsersOrderedBySearchTime() {
        assertUsersInOrder(TestUser.usersList)
    }

    fun withUsersOrderedByRank() {
        assertUsersInOrder(TestUser.usersListSortedByRank)
    }

    fun withUsersOrderedByLeaderBoardPosition() {
        assertUsersInOrder(TestUser.usersListSortedByLeaderBoardPosition)
    }

    private fun assertUsersInOrder(usersList: List<User>) {
        assertRecyclerViewItemCount(R.id.search_history_list, 3)

        assertRecyclerViewItemsInOrder(R.id.search_history_list, usersInOrder(usersList))
    }

    private fun usersInOrder(users: List<User>): List<Matcher<View>> {
        return users.map { hasName(it.name) }
    }

    private fun hasName(name: String): Matcher<View> =
            hasDescendant(allOf(
                    withId(R.id.search_history_item_name),
                    withText(name)
            ))
}