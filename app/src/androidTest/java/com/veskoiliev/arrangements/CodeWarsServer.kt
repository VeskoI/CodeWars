package com.veskoiliev.arrangements

import com.veskoiliev.matchers.HttpMethod
import com.veskoiliev.matchers.RecordedRequestMatchers.onHttpMethod
import com.veskoiliev.matchers.RecordedRequestMatchers.pathContains
import com.veskoiliev.rule.mockwebserver.MockResponseBuilder.Companion.withResponse
import com.veskoiliev.rule.mockwebserver.MockWebServerRule
import org.hamcrest.Matchers.allOf

private const val PATH_SEARCH_USER = ""

class CodeWarsServer(serverRule: MockWebServerRule) : ApiServerArrangements(serverRule) {

    fun respondsWithErrorWhenSearchingForUser() {
        respondOn(
                allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_SEARCH_USER)),
                withResponse().notFoundError()
        )
    }
}