package com.veskoiliev.codewars.domain

/**
 * Abstraction around the system clock that can be easily mocked.
 */
interface TimeProvider {
    fun currentTimeMillis(): Long
}