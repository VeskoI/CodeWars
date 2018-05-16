package com.veskoiliev.tests

import com.veskoiliev.BaseInstrumentationTestCase
import com.veskoiliev.TEST_USER_NAME
import org.junit.Test

class SearchScreenTest : BaseInstrumentationTestCase() {

    @Test
    fun willShowErrorIfUserNotFound() {
        given.codeWarsServer.respondsWithErrorWhenSearchingForUser()

        `when`.user.launchesTheApp()
        `when`.user.onSearchScreen.searchesForUser(TEST_USER_NAME)

        then.user.sees.searchUserScreen.withUserNotFoundError()
    }

    @Test
    fun willOpenChallengeListIfUserFound() {
        given.codeWarsServer.findsUserSuccessfully()

        `when`.user.launchesTheApp()
        `when`.user.onSearchScreen.searchesForUser(TEST_USER_NAME)

        then.user.sees.challengeListScreen
    }

    @Test
    fun willDisplaySearchHistoryOrderedBySearchTimeByDefault() {
        given.database.hasSearchHistoryInDatabase()

        `when`.user.launchesTheApp()

        then.user.sees.searchUserScreen.withUsersOrderedBySearchTime()
    }

    @Test
    fun willReOrderItemsBasedOnSelectedSortingOption() {
        given.database.hasSearchHistoryInDatabase()

        `when`.user.launchesTheApp()

        `when`.user.onSearchScreen.sortsItemsByRank()
        then.user.sees.searchUserScreen.withUsersOrderedByRank()

        `when`.user.onSearchScreen.sortsItemsByLeaderBoardPosition()
        then.user.sees.searchUserScreen.withUsersOrderedByLeaderBoardPosition()
    }
}