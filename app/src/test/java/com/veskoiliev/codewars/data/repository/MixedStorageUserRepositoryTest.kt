package com.veskoiliev.codewars.data.repository

import com.veskoiliev.codewars.data.local.db.UserDao
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.CodeWarsRestApi
import com.veskoiliev.codewars.domain.TimeProvider
import com.veskoiliev.codewars.testdata.TestUser
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MixedStorageUserRepositoryTest {

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

    @Before
    fun setUp() {
        underTest = MixedStorageUserRepository(userDao, restApi, timeProvider)
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