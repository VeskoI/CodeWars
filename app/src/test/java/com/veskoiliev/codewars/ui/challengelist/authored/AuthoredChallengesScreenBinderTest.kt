package com.veskoiliev.codewars.ui.challengelist.authored

import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.testdata.TestAuthoredChallenge
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthoredChallengesScreenBinderTest {

    @Mock
    private lateinit var view: AuthoredChallengesView

    private lateinit var underTest: AuthoredChallengesScreenBinder

    @Before
    fun setUp() {
        underTest = AuthoredChallengesScreenBinder(view)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
    }

    @Test
    fun willDisplayEmptyViewWhenBindingEmptyChallengesList() {
        underTest.bindChallengesResource(Resource.SuccessResource(emptyList()))

        verify(view).toggleLoading(false)
        verify(view).toggleEmptyView(true)
    }

    @Test
    fun willDisplayChallengesListIfNotEmpty() {
        underTest.bindChallengesResource(Resource.SuccessResource(TestAuthoredChallenge.authoredChallengesList()))

        verify(view).toggleLoading(false)
        verify(view).toggleEmptyView(false)
        verify(view).showChallengesList(TestAuthoredChallenge.authoredChallengesList())
    }

    @Test
    fun willDisplayErrorIfInvalidResourceIsReceived() {
        underTest.bindChallengesResource(null)

        verify(view).toggleLoading(false)
        verify(view).displayError(R.string.error_generic_message)
    }

    @Test
    fun willDisplayLoadingIfLoadingResourceReceived() {
        underTest.bindChallengesResource(Resource.LoadingResource())

        verify(view).toggleLoading(true)
    }

    @Test
    fun willDisplayTheErrorFromErrorResource() {
        underTest.bindChallengesResource(Resource.ErrorResource(R.string.error_fetching_authored_challenges))

        verify(view).toggleLoading(false)
        verify(view).displayError(R.string.error_fetching_authored_challenges)
    }
}