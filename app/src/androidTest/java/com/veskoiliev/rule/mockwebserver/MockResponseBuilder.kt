package com.veskoiliev.rule.mockwebserver

import okhttp3.mockwebserver.MockResponse

class MockResponseBuilder private constructor() {

    val mockResponse: MockResponse = MockResponse()

    fun body(body: String): MockResponseBuilder {
        mockResponse.setBody(body)
        return this
    }

    fun notFoundError(): MockResponseBuilder {
        return responseCode(404)
    }

    fun responseCode(responseCode: Int): MockResponseBuilder {
        mockResponse.setResponseCode(responseCode)
        return this
    }

    companion object {

        fun withResponse(): MockResponseBuilder {
            return MockResponseBuilder()
        }
    }

}
