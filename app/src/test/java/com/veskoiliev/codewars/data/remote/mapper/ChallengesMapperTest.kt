package com.veskoiliev.codewars.data.remote.mapper

import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallengesList
import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallengesResponseModel
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChallengesMapperTest {

    private val underTest = ChallengesMapper()

    @Test
    fun willMapCompletedChallengesSuccessfully() {
        // This test heavily relies on the test values from TestCompletedChallenge
        val result = underTest.mapCompletedChallenges(completedChallengesResponseModel)

        assertEquals(completedChallengesList, result)
    }
}