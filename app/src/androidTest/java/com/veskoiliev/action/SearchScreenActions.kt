package com.veskoiliev.action

import android.support.test.espresso.matcher.ViewMatchers.withId
import com.veskoiliev.action.ViewActions.selectView
import com.veskoiliev.action.ViewActions.typeText
import com.veskoiliev.codewars.R

class SearchScreenActions {

    fun searchesForUser(userName: String) {
        typeText(R.id.search_username_field, text = userName)
        selectView(withId(R.id.search_btn))
    }
}