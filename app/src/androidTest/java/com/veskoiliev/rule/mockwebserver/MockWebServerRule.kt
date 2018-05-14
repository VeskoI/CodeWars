package com.veskoiliev.rule.mockwebserver

import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matcher
import org.junit.rules.ExternalResource
import java.io.IOException

/**
 * Used to initiate, and provide an API to program a server running locally on the device.
 */
class MockWebServerRule : ExternalResource() {

    private val server = MockWebServer()
    private val dispatcher = MockDispatcher()

    override fun before() {
        server.setDispatcher(dispatcher)
        server.start(PORT)
    }

    override fun after() {
        shutdownServer()
    }

    private fun shutdownServer() {
        try {
            server.shutdown()
        } catch (e: IOException) {
            throw RuntimeException("Failed to shutdown server", e)
        }

    }

    /**
     * Used to program the server. The method accepts a [MockResponseBuilder] which holds info
     * about the desired response and provides a continuation object which can be used to match the
     * response to a request.
     */
    fun responds(mockResponseBuilder: MockResponseBuilder): ResponseContinuation {
        return ResponseContinuation(mockResponseBuilder)
    }

    inner class ResponseContinuation(private val responseBuilder: MockResponseBuilder) {

        /**
         * Used to provide a request matcher used to map the response to
         */
        fun on(requestMatcher: Matcher<in RecordedRequest>): MockWebServerRule {
            dispatcher.responds(requestMatcher, responseBuilder)
            return this@MockWebServerRule
        }
    }

    companion object {
        const val PORT = 9999
        const val BASE_URL = "http://localhost:$PORT"
    }
}
