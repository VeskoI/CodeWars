package com.veskoiliev.codewars.ui.search

import android.support.annotation.StringRes
import com.veskoiliev.codewars.data.local.model.User

interface SearchUserView : UserSelectedListener {
    fun hideUserHistoryList()
    fun showUserHistoryList(usersList: List<User>)
    fun toggleEmptyView(visible: Boolean)
    fun displayError(@StringRes errorMessage: Int)
    fun toggleLoading(visible: Boolean)
}
