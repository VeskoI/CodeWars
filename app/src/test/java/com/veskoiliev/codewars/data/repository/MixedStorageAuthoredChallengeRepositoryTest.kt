package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.local.db.AuthoredChallengeDao
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.testdata.TestAuthoredChallenge
import com.veskoiliev.codewars.testdata.TestUserModel.userName
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MixedStorageAuthoredChallengeRepositoryTest {

    @Mock
    private lateinit var challengesDao: AuthoredChallengeDao

    @Mock
    private lateinit var restApi: CodeWarsRestApi

    private lateinit var underTest: MixedStorageAuthoredChallengeRepository

    private val challengesObserver = TestObserver<List<AuthoredChallenge>>()
    private val authoredChallengesList = TestAuthoredChallenge.authoredChallengesList()

    @Before
    fun setUp() {
        underTest = MixedStorageAuthoredChallengeRepository(challengesDao, restApi, Schedulers.trampoline())
    }

    @Test
    fun willReturnChallengesFromLocalStorageIfAvailable() {
        whenLocalStorageContainsChallenges(Single.just(authoredChallengesList))

        underTest.authoredChallenges(userName).subscribe(challengesObserver)

        challengesObserver.assertValue(authoredChallengesList)
        verifyNoApiCallMade()
    }

    @Test
    fun willFetchDataFromNetworkWhenNotAvailableInLocalStorage() {
        whenLocalStorageContainsChallenges(Single.error(Throwable()))
        whenFetchingChallengesResultsIn(Single.just(authoredChallengesList))

        underTest.authoredChallenges(userName).subscribe(challengesObserver)

        challengesObserver.assertValue(authoredChallengesList)
        verify(challengesDao).save(authoredChallengesList)
    }

    @Test
    fun willFetchDataFromNetworkWhenNothingSavedInLocalStorage() {
        whenLocalStorageContainsChallenges(Single.just(emptyList()))
        whenFetchingChallengesResultsIn(Single.just(authoredChallengesList))

        underTest.authoredChallenges(userName).subscribe(challengesObserver)

        challengesObserver.assertValue(authoredChallengesList)
        verify(challengesDao).save(authoredChallengesList)
    }

    @Test
    fun willReturnErrorIfFetchingDataFails() {
        val networkError = Throwable("network error")
        whenLocalStorageContainsChallenges(Single.error(Throwable("local error")))
        whenFetchingChallengesResultsIn(Single.error(networkError))

        underTest.authoredChallenges(userName).subscribe(challengesObserver)

        challengesObserver.assertError(networkError)
    }

    private fun whenLocalStorageContainsChallenges(result: Single<List<AuthoredChallenge>>) {
        given(challengesDao.getAuthoredChallenges(userName)).willReturn(result)
    }

    private fun whenFetchingChallengesResultsIn(result: Single<List<AuthoredChallenge>>) {
        given(restApi.getAuthoredChallenges(userName)).willReturn(result)
    }

    private fun verifyNoApiCallMade() {
        verifyZeroInteractions(restApi)
    }
}