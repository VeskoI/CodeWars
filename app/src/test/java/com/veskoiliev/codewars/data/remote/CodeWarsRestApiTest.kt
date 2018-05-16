package com.veskoiliev.codewars.data.remote

import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.data.remote.mapper.ChallengesMapper
import com.veskoiliev.codewars.data.remote.mapper.UserMapper
import com.veskoiliev.codewars.data.remote.model.UserModel
import com.veskoiliev.codewars.data.remote.model.challenge.CompletedChallengesResponseModel
import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallengesList
import com.veskoiliev.codewars.testdata.TestCompletedChallenge.completedChallengesResponseModel
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

    @Mock
    private lateinit var challengesMapper: ChallengesMapper

    private lateinit var underTest: CodeWarsRestApi
    private val userName = "userName"
    private val requestPage = 2
    private val getUserObserver = TestObserver<User>()
    private val getCompletedChallengesObserver = TestObserver<List<CompletedChallenge>>()
    private val networkError = Throwable()

    @Before
    fun setUp() {
        underTest = CodeWarsRestApi(service, userMapper, challengesMapper)
    }

    @Test
    fun willReturnErrorIfGetUserFails() {
        whenGetUserCallResultsIn(Single.error(networkError))

        underTest.getUser(userName).subscribe(getUserObserver)

        getUserObserver.assertError(networkError)
    }

    @Test
    fun willReturnMappedUserCorrectly() {
        val networkModel = TestUserModel.userModel
        whenGetUserCallResultsIn(Single.just(networkModel))
        whenNetworkUserModelMapsTo(networkModel, TestUser.user)

        underTest.getUser(userName).subscribe(getUserObserver)

        getUserObserver.assertValue(TestUser.user)
    }

    @Test
    fun willThrowErrorIfFetchingCompletedChallengesFails() {
        whenGetCompletedChallengesResultsIn(Single.error(networkError))

        underTest.getCompletedChallenges(userName, requestPage).subscribe(getCompletedChallengesObserver)

        getCompletedChallengesObserver.assertError(networkError)
    }

    @Test
    fun willReturnCompletedChallengesCorrectly() {
        val networkModel = completedChallengesResponseModel
        whenGetCompletedChallengesResultsIn(Single.just(networkModel))
        whenCompletedChallengesMapCorrectly(networkModel, completedChallengesList)

        underTest.getCompletedChallenges(userName, requestPage).subscribe(getCompletedChallengesObserver)

        getCompletedChallengesObserver.assertValue(completedChallengesList)
    }

    private fun whenGetUserCallResultsIn(result: Single<UserModel>) {
        given(service.getUser(userName)).willReturn(result)
    }

    private fun whenNetworkUserModelMapsTo(networkModel: UserModel, user: User) {
        given(userMapper.map(networkModel)).willReturn(user)
    }

    private fun whenGetCompletedChallengesResultsIn(result: Single<CompletedChallengesResponseModel>) {
        given(service.getCompletedChallenges(userName, requestPage)).willReturn(result)
    }

    private fun whenCompletedChallengesMapCorrectly(networkModel: CompletedChallengesResponseModel, completedChallengesList: List<CompletedChallenge>) {
        given(challengesMapper.mapCompletedChallenges(networkModel)).willReturn(completedChallengesList)
    }
}