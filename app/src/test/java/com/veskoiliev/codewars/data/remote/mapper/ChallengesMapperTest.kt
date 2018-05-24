package com.veskoiliev.codewars.data.remote.mapper

import com.veskoiliev.codewars.testdata.TestAuthoredChallenge
import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallengesList
import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallengesResponseModel
import com.veskoiliev.codewars.testdata.TestUserModel.userName
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChallengesMapperTest {

    private val underTest = ChallengesMapper()
    private val page = 7

    @Test
    fun willMapCompletedChallengesSuccessfully() {
        // This test heavily relies on the test values from TestCompletedChallenge
        val result = underTest.mapCompletedChallenges(completedChallengesResponseModel, page)

        assertEquals(completedChallengesList(page), result)
    }

    @Test
    fun willMapAuthoredChallengesCorrectly() {
        val result = underTest.mapAuthoredChallenges(TestAuthoredChallenge.authoredChallengesResponseModel, userName)

        assertEquals(TestAuthoredChallenge.authoredChallengesList(userName), result)
    }
}