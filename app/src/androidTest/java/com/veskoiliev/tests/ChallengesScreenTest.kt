package com.veskoiliev.tests

import com.veskoiliev.BaseInstrumentationTestCase
import org.junit.Test

class ChallengesScreenTest : BaseInstrumentationTestCase() {

    @Test
    fun willDisplayErrorWhenLoadingCompletedChallenges() {
        given.codeWarsServer.respondsWithErrorWhenFetchingCompletedChallenges()
        givenUserHasOpenedChallengeListScreen()

        then.user.sees.challengeListScreen
                .withCompletedChallengesTab()
                .withErrorLoadingCompletedChallenges()
                .withRetryButton()
    }

    @Test
    fun willDisplayCompletedChallengesSuccessfully() {
        given.codeWarsServer.returnsCompletedChallengesSuccessfully()
        givenUserHasOpenedChallengeListScreen()

        then.user.sees.challengeListScreen
                .withCompletedChallengesTab()
                .withCompletedChallengesList()
                .withoutRetryButton()
    }

    @Test
    fun willDisplayErrorWhenLoadingAuthoredChallengesFails() {
        given.codeWarsServer.respondsWithErrorWhenFetchingAuthoredChallenges()
        givenUserHasOpenedChallengeListScreen()
        `when`.user.onChallengeScreen.selectsAuthoredTab()

        then.user.sees.challengeListScreen
                .withErrorLoadingAuthoredChallenges()
    }

    @Test
    fun willDisplayAuthoredChallengesSuccessfully() {
        given.codeWarsServer.returnsAuthoredChallengesSuccessfully()
        givenUserHasOpenedChallengeListScreen()
        `when`.user.onChallengeScreen.selectsAuthoredTab()

        then.user.sees.challengeListScreen
                .withAuthoredChallengesList()
    }

    private fun givenUserHasOpenedChallengeListScreen() {
        given.database.hasSearchHistoryInDatabase()

        `when`.user.launchesTheApp()
        `when`.user.onSearchScreen.selectsUserFromHistory("name")
    }
}