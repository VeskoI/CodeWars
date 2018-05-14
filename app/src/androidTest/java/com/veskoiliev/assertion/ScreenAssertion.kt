package com.veskoiliev.assertion

/**
 * Wrapper for various screen assertions.
 *
 * Typical usage is:
 * _then.user.sees.someScreenIsDisplayed()_
 */
class ScreenAssertion {

    val searchUserScreen: SearchScreenAssertions by lazy { SearchScreenAssertions() }
}
