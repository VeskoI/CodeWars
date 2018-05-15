package com.veskoiliev.codewars.ui.search

import com.veskoiliev.codewars.data.local.model.User

interface UserClickListener {
    fun onUserSelected(user: User)
}