package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.ViewModel
import com.veskoiliev.codewars.data.repository.UserRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun onSearchClicked(userName: String) {
        // TODO not implemented
    }

    fun fetchSearchHistory() {
        // TODO not implemented
    }
}