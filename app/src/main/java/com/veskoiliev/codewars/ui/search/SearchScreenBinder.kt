package com.veskoiliev.codewars.ui.search

import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.User
import javax.inject.Inject

/**
 * Binds complex UI objects to Android's View system.
 *
 * It's a lightweight presenter from the MVP world.
 */
class SearchScreenBinder @Inject constructor(private val view: SearchUserView) {

    fun bindSearchHistory(searchHistoryUsers: List<User>) = if (searchHistoryUsers.isEmpty()) {
        view.toggleEmptyView(true)
        view.hideUserHistoryList()
    } else {
        view.toggleEmptyView(false)
        view.showUserHistoryList(searchHistoryUsers)
    }

    fun bindSearchedUserResource(searchedUserResource: Resource<User>?) {
        when (searchedUserResource) {
            is Resource.LoadingResource -> {
                view.toggleLoading(true)
            }
            is Resource.ErrorResource -> {
                view.toggleLoading(false)
                view.displayError(searchedUserResource.errorMessage)
            }
            is Resource.SuccessResource -> {
                view.toggleLoading(false)
                view.onUserSelected(searchedUserResource.data!!)
            }
            else -> {
                view.toggleLoading(false)
                view.displayError(R.string.error_generic_message)
            }
        }
    }
}