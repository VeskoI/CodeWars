package com.veskoiliev.codewars.ui.search

import com.veskoiliev.codewars.data.local.model.User

interface SearchUserView {
    fun hideUserHistoryList()
    fun showUserHistoryList(usersList: List<User>)
    fun toggleEmptyView(visible: Boolean)
}
