package com.veskoiliev.codewars.ext

import android.view.View

fun View.toggleVisibility(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}