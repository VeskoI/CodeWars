package com.veskoiliev.codewars.data.local.db

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.veskoiliev.codewars.data.NetworkState
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallenge
import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallengesList
import com.veskoiliev.codewars.testdata.TestUserModel.userName
import com.veskoiliev.codewars.utils.LiveDataTestUtils.observeLiveData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class CompletedChallengeBoundaryCallbackTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var restApi: CodeWarsRestApi

    private lateinit var underTest: CompletedChallengeBoundaryCallback
    private lateinit var networkStateObserver: Observer<NetworkState>
    private val initialPage = 0
    private var success = 0

    @Suppress("UNUSED_ANONYMOUS_PARAMETER") // kept for readability
    @Before
    fun setUp() {
        underTest = CompletedChallengeBoundaryCallback(userName, restApi, Schedulers.trampoline(), Schedulers.trampoline()) { userName, result ->
            success++
        }
        networkStateObserver = observeLiveData(underTest.networkState())
    }

    @Test
    fun successfulInitialCallWillTriggerSuccess() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.just(completedChallengesList))

        underTest.onZeroItemsLoaded()

        verifyNetworkStates(NetworkState.LOADING, NetworkState.LOADED)
        verifySuccessfulCallbackExecuted()
    }

    @Test
    fun initialLoadingCanBeTriggeredOnlyOnce() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.just(completedChallengesList).delay(5, TimeUnit.SECONDS))

        underTest.onZeroItemsLoaded()
        underTest.onZeroItemsLoaded()

        verify(restApi).getCompletedChallenges(userName, initialPage)
        verifyNetworkStates(NetworkState.LOADING)
        verifyResponseCallbackNotTriggered()
    }

    @Test
    fun failedInitialCallWillTriggerError() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.error(Throwable("message")))

        underTest.onZeroItemsLoaded()

        verifyNetworkStates(NetworkState.LOADING, NetworkState.error("message"))
        verifyResponseCallbackNotTriggered()
    }

    @Test
    fun successfulLoadingOfMoreDataWillTriggerSuccess() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.just(completedChallengesList))

        underTest.onItemAtEndLoaded(completedChallenge)

        verifyNetworkStates(NetworkState.LOADING, NetworkState.LOADED)
        verifySuccessfulCallbackExecuted()
    }

    @Test
    fun fetchingMoreDataCanBeTriggeredOnlyOnce() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.just(completedChallengesList).delay(5, TimeUnit.SECONDS))

        underTest.onItemAtEndLoaded(completedChallenge)
        underTest.onItemAtEndLoaded(completedChallenge)

        verify(restApi).getCompletedChallenges(userName, initialPage)
        verifyNetworkStates(NetworkState.LOADING)
        verifyResponseCallbackNotTriggered()
    }

    @Test
    fun failedLoadingCallWillTriggerError() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.error(Throwable("message")))

        underTest.onItemAtEndLoaded(completedChallenge)

        verifyNetworkStates(NetworkState.LOADING, NetworkState.error("message"))
        verifyResponseCallbackNotTriggered()
    }

    @Test
    fun initialAndSubsequentFetchWillLoadDifferentPages() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.just(completedChallengesList))
        whenFetchingCompletedChallengesResultsIn(initialPage + 1, Single.just(completedChallengesList))

        underTest.onZeroItemsLoaded()
        underTest.onItemAtEndLoaded(completedChallenge)

        verifyNetworkStates(NetworkState.LOADING, NetworkState.LOADED, NetworkState.LOADING, NetworkState.LOADED)
        verifySuccessfulCallbackExecuted(numberOfTimes = 2)
    }

    @Test
    fun initialAndSubsequentFailureWillSignalCorrectNetworkStates() {
        whenFetchingCompletedChallengesResultsIn(initialPage, Single.just(completedChallengesList))
        whenFetchingCompletedChallengesResultsIn(initialPage + 1, Single.error(Throwable("message")))

        underTest.onZeroItemsLoaded()
        underTest.onItemAtEndLoaded(completedChallenge)

        verifyNetworkStates(NetworkState.LOADING, NetworkState.LOADED, NetworkState.LOADING, NetworkState.error("message"))
        verifySuccessfulCallbackExecuted(numberOfTimes = 1)
    }

    private fun whenFetchingCompletedChallengesResultsIn(page: Int, result: Single<List<CompletedChallenge>>) {
        given(restApi.getCompletedChallenges(userName, page)).willReturn(result)
    }

    private fun verifyNetworkStates(vararg networkStates: NetworkState) {
        val inOrder = inOrder(networkStateObserver)
        networkStates.forEach {
            inOrder.verify(networkStateObserver).onChanged(it)
        }
    }

    private fun verifySuccessfulCallbackExecuted(numberOfTimes: Int = 1) {
        assertEquals(numberOfTimes, success)
    }

    private fun verifyResponseCallbackNotTriggered() {
        assertEquals(0, success)
    }
}