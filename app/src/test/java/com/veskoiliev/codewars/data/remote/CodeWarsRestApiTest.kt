package com.veskoiliev.codewars.data.remote

import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.remote.mapper.UserMapper
import com.veskoiliev.codewars.data.remote.model.UserModel
import com.veskoiliev.codewars.testdata.TestUser
import com.veskoiliev.codewars.testdata.TestUserModel
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CodeWarsRestApiTest {

    @Mock
    private lateinit var service: CodeWarsApiService

    @Mock
    private lateinit var userMapper: UserMapper

    private lateinit var underTest: CodeWarsRestApi
    private val userName = "userName"
    private val getUserObserver = TestObserver<User>()
    private val networkError = Throwable()

    @Before
    fun setUp() {
        underTest = CodeWarsRestApi(service, userMapper)
    }

    @Test
    fun willReturnErrorIfApiFails() {
        whenGetUserCallResultsIn(Single.error(networkError))

        underTest.getUser(userName).subscribe(getUserObserver)

        getUserObserver.assertError(networkError)
    }

    @Test
    fun willReturnUserCorrectlyAfterMapping() {
        val networkModel = TestUserModel.userModel
        whenGetUserCallResultsIn(Single.just(networkModel))
        whenNetworkModelMapsTo(networkModel, TestUser.user)

        underTest.getUser(userName).subscribe(getUserObserver)

        getUserObserver.assertValue(TestUser.user)
    }

    private fun whenGetUserCallResultsIn(result: Single<UserModel>) {
        given(service.getUser(userName)).willReturn(result)
    }

    private fun whenNetworkModelMapsTo(networkModel: UserModel, user: User) {
        given(userMapper.map(networkModel)).willReturn(user)
    }
}