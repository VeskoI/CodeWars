package com.veskoiliev.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeDiagnosingMatcher

object RecordedRequestMatchers {

    /**
     * Match requests that _contain_ the given path.
     */
    fun pathContains(path: String): Matcher<RecordedRequest> {
        return object : TypeSafeDiagnosingMatcher<RecordedRequest>() {
            override fun matchesSafely(recordedRequest: RecordedRequest, mismatchDescription: Description) =
                    recordedRequest.path.contains(path)

            override fun describeTo(description: Description) {
                description.appendText("a request that contains the path: $path")
            }
        }
    }

    /**
     * Match requests that have the given HTTP method.
     */
    fun onHttpMethod(httpMethod: HttpMethod): Matcher<RecordedRequest> {
        return object : TypeSafeDiagnosingMatcher<RecordedRequest>() {
            override fun matchesSafely(recordedRequest: RecordedRequest, mismatchDescription: Description): Boolean {
                val method = recordedRequest.method
                val result = method.equals(httpMethod.name, ignoreCase = true)
                if (!result) {
                    mismatchDescription.appendText("method was: $httpMethod")
                }
                return result
            }

            override fun describeTo(description: Description) {
                description.appendText("a request with HTTP method: $httpMethod")
            }
        }
    }

    /**
     * Composite matcher allowing the provided matcher to only match once.
     * All subsequent checks will fail.
     *
     * Example usage: ensure only the first API call to a specific API fails, allowing subsequent ones to pass.
     */
    fun oneCallTo(matcher: Matcher<in RecordedRequest>): Matcher<RecordedRequest> {
        return object : BaseMatcher<RecordedRequest>() {
            internal var matchCount = 0

            override fun matches(item: Any): Boolean {
                if (matchCount == 1) {
                    return false
                }
                val matched = matcher.matches(item)
                if (matched) {
                    matchCount++
                }
                return matched
            }

            override fun describeTo(description: Description) {
                description.appendText("One call to ")
                matcher.describeTo(description)
            }
        }
    }
}
