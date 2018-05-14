package com.veskoiliev.rule.mockwebserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * Matches server requests to appropriate responses.
 */
class MockDispatcher : Dispatcher() {

    private val responseMap = linkedMapOf<Matcher<in RecordedRequest>, MockResponseBuilder>()

    override fun dispatch(recordedRequest: RecordedRequest): MockResponse {
        val mismatchDescription = StringDescription()
        val response = matchRequest(recordedRequest, mismatchDescription)
        return response?.mockResponse ?: MockResponse()
                .setBody("No response found for the request\n" + mismatchDescription.toString())
                .setResponseCode(404)
    }

    private fun matchRequest(recordedRequest: RecordedRequest, mismatchDescription: Description): MockResponseBuilder? {
        for ((requestMatcher, response) in responseMap) {
            if (requestMatcher.matches(recordedRequest)) {
                return response
            } else {
                mismatchDescription.appendText("\n--> Matcher:\n")
                        .appendText("    ")
                requestMatcher.describeTo(mismatchDescription)
                mismatchDescription.appendText("\n")
                mismatchDescription.appendText("    ")
                requestMatcher.describeMismatch(recordedRequest, mismatchDescription)
            }
        }
        return null
    }

    fun responds(requestMatcher: Matcher<in RecordedRequest>, response: MockResponseBuilder) {
        responseMap[requestMatcher] = response
    }
}
