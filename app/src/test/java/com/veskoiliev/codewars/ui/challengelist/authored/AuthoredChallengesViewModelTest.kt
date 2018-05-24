package com.veskoiliev.codewars.ui.challengelist.authored

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.data.repository.AuthoredChallengeRepository
import com.veskoiliev.codewars.testdata.TestAuthoredChallenge
import com.veskoiliev.codewars.testdata.TestUserModel.userName
import com.veskoiliev.codewars.utils.LiveDataTestUtils.observeLiveData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthoredChallengesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var authoredChallengesRepository: AuthoredChallengeRepository

    private lateinit var underTest: AuthoredChallengesViewModel
    private lateinit var challengesObserver: Observer<Resource<List<AuthoredChallenge>>>
    private val authoredChallengesList = TestAuthoredChallenge.authoredChallengesList()

    @Before
    fun setUp() {
        underTest = AuthoredChallengesViewModel(authoredChallengesRepository, Schedulers.trampoline())
        challengesObserver = observeLiveData(underTest.authoredChallenges())
    }

    @Test
    fun willDisplayErrorWhenLoadingChallengesFails() {
        whenLoadingChallengesResultsIn(Single.error(Throwable()))

        underTest.init(userName)

        verify(challengesObserver).onChanged(Resource.LoadingResource())
        verify(challengesObserver).onChanged(Resource.ErrorResource(R.string.error_fetching_authored_challenges))
    }

    @Test
    fun willDisplayChallengesSuccessfully() {
        whenLoadingChallengesResultsIn(Single.just(authoredChallengesList))

        underTest.init(userName)

        verify(challengesObserver).onChanged(Resource.LoadingResource())
        verify(challengesObserver).onChanged(Resource.SuccessResource(authoredChallengesList))
    }

    private fun whenLoadingChallengesResultsIn(result: Single<List<AuthoredChallenge>>) {
        given(authoredChallengesRepository.authoredChallenges(userName)).willReturn(result)
    }
}