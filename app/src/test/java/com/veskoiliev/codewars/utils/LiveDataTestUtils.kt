package com.veskoiliev.codewars.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

object LiveDataTestUtils {

    fun <T> observeLiveData(liveData: LiveData<T>): Observer<T> {
        val observer = mock<Observer<T>>()
        liveData.observeForever(observer)
        return observer
    }
}