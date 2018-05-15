package com.veskoiliev.codewars.ui.search

import com.veskoiliev.codewars.data.local.model.User

interface UserSelectedListener {
    fun onUserSelected(user: User)
}