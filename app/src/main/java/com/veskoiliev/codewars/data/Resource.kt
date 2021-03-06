package com.veskoiliev.codewars.data

import android.support.annotation.StringRes

/**
 * A generic class that describes data with a status.
 */
sealed class Resource<out T>(val data: T? = null) {

    /**
     * A generic class that describes data with a loading status.
     */
    class LoadingResource<out T> : Resource<T>()

    /**
     * A generic class that describes data with an error status.
     */
    data class ErrorResource<out T>(@StringRes val errorMessage: Int) : Resource<T>()

    /**
     * A generic class that describes data with a success status.
     */
    class SuccessResource<out T>(data: T) : Resource<T>(data)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resource<*>

        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        return data?.hashCode() ?: 0
    }
}