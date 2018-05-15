package com.veskoiliev.codewars.domain

import javax.inject.Inject

class SystemTimeProvider @Inject constructor() : TimeProvider {
    override fun currentTimeMillis() = System.currentTimeMillis()
}