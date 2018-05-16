package com.veskoiliev.action

import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.veskoiliev.action.ViewActions.selectView
import com.veskoiliev.action.ViewActions.typeText
import com.veskoiliev.codewars.R

class SearchScreenActions {

    fun searchesForUser(userName: String) {
        typeText(R.id.search_username_field, text = userName)
        selectView(withId(R.id.search_btn))
    }

    fun sortsItemsByRank() {
        openSortMenu()
        selectView(withText(R.string.sort_by_rank)) // asserting by text is safer than id when it comes to sub-menus
    }

    fun sortsItemsByLeaderBoardPosition() {
        openSortMenu()
        selectView(withText(R.string.sort_by_leaderboard_score))    // asserting by text is safer than id when it comes to sub-menus
    }

    private fun openSortMenu() {
        selectView(withId(R.id.menu_item_sort))
    }
}