package com.veskoiliev.arrangements

import com.veskoiliev.matchers.HttpMethod
import com.veskoiliev.matchers.RecordedRequestMatchers.onHttpMethod
import com.veskoiliev.matchers.RecordedRequestMatchers.pathContains
import com.veskoiliev.rule.mockwebserver.MockResponseBuilder.Companion.withResponse
import com.veskoiliev.rule.mockwebserver.MockWebServerRule
import com.veskoiliev.util.Assets
import org.hamcrest.Matchers.allOf
import java.net.HttpURLConnection.HTTP_BAD_REQUEST

private const val TEST_USERNAME = "username"   // comes from TestUser
private const val PATH_SEARCH_USER = "v1/users"
private const val PATH_COMPLETED_CHALLENGES_PAGE_0 = "v1/users/$TEST_USERNAME/code-challenges/completed?page=0"
private const val PATH_COMPLETED_CHALLENGES_PAGE_1 = "v1/users/$TEST_USERNAME/code-challenges/completed?page=1"
private const val PATH_AUTHORED_CHALLENGES = "v1/users/$TEST_USERNAME/code-challenges/authored"

class CodeWarsServer(serverRule: MockWebServerRule) : ApiServerArrangements(serverRule) {

    fun respondsWithErrorWhenSearchingForUser() {
        respondOn(
                allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_SEARCH_USER)),
                withResponse().notFoundError()
        )
    }

    fun findsUserSuccessfully() {
        respondOn(
                allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_SEARCH_USER)),
                withResponse().body(Assets.GET_USER_RESPONSE)
        )
    }

    fun respondsWithErrorWhenFetchingCompletedChallenges() {
        respondOn(
                allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_COMPLETED_CHALLENGES_PAGE_0)),
                withResponse().responseCode(HTTP_BAD_REQUEST)
        )
    }

    fun returnsCompletedChallengesSuccessfully() {
        fun firstPageIsReturnedSuccessfully() {
            respondOn(
                    allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_COMPLETED_CHALLENGES_PAGE_0)),
                    withResponse().body(Assets.COMPLETED_CHALLENGES_RESPONSE)
            )
        }

        fun secondPageIsReturnedSuccessfully() {
            respondOn(
                    allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_COMPLETED_CHALLENGES_PAGE_1)),
                    withResponse().body(Assets.EMPTY_COMPLETED_CHALLENGES_RESPONSE)
            )
        }

        firstPageIsReturnedSuccessfully()

        secondPageIsReturnedSuccessfully()
    }


    fun respondsWithErrorWhenFetchingAuthoredChallenges() {
        respondOn(
                allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_AUTHORED_CHALLENGES)),
                withResponse().responseCode(HTTP_BAD_REQUEST)
        )
    }

    fun returnsAuthoredChallengesSuccessfully() {
        respondOn(
                allOf(onHttpMethod(HttpMethod.GET), pathContains(PATH_AUTHORED_CHALLENGES)),
                withResponse().body(Assets.AUTHORED_CHALLENGES_RESPONSE)
        )
    }
}