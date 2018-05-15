package com.veskoiliev.codewars.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.veskoiliev.codewars.data.local.db.UserDao
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.domain.TimeProvider
import com.veskoiliev.codewars.testdata.TestUser
import com.veskoiliev.codewars.utils.LiveDataTestUtils
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MixedStorageUserRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var restApi: CodeWarsRestApi

    @Mock
    private lateinit var timeProvider: TimeProvider

    private lateinit var underTest: MixedStorageUserRepository
    private val userName = "userName"
    private val userObserver = TestObserver<User>()
    private val networkError = Throwable()
    private val userNotAvailableError = Single.error<User>(Throwable("user not available"))
    private val currentTime = 1232131L

    private val sortOption = SortOption.RANK
    private val searchHistoryLiveData = MutableLiveData<List<User>>()
    private lateinit var searchHistoryObserver: Observer<List<User>>

    @Before
    fun setUp() {
        underTest = MixedStorageUserRepository(userDao, restApi, timeProvider, Schedulers.trampoline())
    }

    @Test
    fun willGetUserFromLocalStorageIfAvailable() {
        whenLocalStorageContainsUser(Single.just(TestUser.user))

        underTest.getUser(userName).subscribe(userObserver)

        userObserver.assertValue(TestUser.user)
        verifyZeroInteractions(restApi)
    }

    @Test
    fun willReturnErrorIfUserNotInLocalDatabaseAndRemoteCallFails() {
        whenLocalStorageContainsUser(userNotAvailableError)
        whenRemoteApiCallResultsIn(Single.error(networkError))

        underTest.getUser(userName).subscribe(userObserver)

        userObserver.assertError(networkError)
    }

    @Test
    fun willSaveUserToLocalStorageAfterSuccessfulFetchFromNetwork() {
        whenLocalStorageContainsUser(userNotAvailableError)
        whenRemoteApiCallResultsIn(Single.just(TestUser.user))
        whenCurrentTimeIs(currentTime)

        underTest.getUser(userName).subscribe(userObserver)

        userObserver.assertValue(TestUser.user)
        verify(userDao).saveUser(TestUser.user.copy(searchedTimestamp = currentTime))
    }

    @Test
    fun willReadSearchHistoryFromDatabaseBySearchTime() {
        given(userDao.getSearchHistoryBySearchTime()).willReturn(searchHistoryLiveData)

        willReadSearchHistoryFromDatabaseBy(SortOption.SEARCH_TIME)
    }

    @Test
    fun willReadSearchHistoryFromDatabaseByRank() {
        given(userDao.getSearchHistoryByRank()).willReturn(searchHistoryLiveData)

        willReadSearchHistoryFromDatabaseBy(SortOption.RANK)
    }

    @Test
    fun willReadSearchHistoryFromDatabaseByLeaderBoardPosition() {
        given(userDao.getSearchHistoryByLeaderBoardPosition()).willReturn(searchHistoryLiveData)

        willReadSearchHistoryFromDatabaseBy(SortOption.LEADER_BOARD_POSITION)
    }

    private fun willReadSearchHistoryFromDatabaseBy(sortOption: SortOption) {
        searchHistoryObserver = LiveDataTestUtils.observeLiveData(underTest.getSearchHistory(sortOption))

        searchHistoryLiveData.value = TestUser.usersList

        searchHistoryObserver.onChanged(TestUser.usersList)
    }

    private fun whenLocalStorageContainsUser(result: Single<User>) {
        given(userDao.getUser(userName)).willReturn(result)
    }

    private fun whenRemoteApiCallResultsIn(result: Single<User>) {
        given(restApi.getUser(userName)).willReturn(result)
    }

    private fun whenCurrentTimeIs(currentTime: Long) {
        given(timeProvider.currentTimeMillis()).willReturn(currentTime)
    }
}