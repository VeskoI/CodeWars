package com.veskoiliev.codewars.ui.search

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.repository.UserRepository
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
class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var underTest: SearchViewModel
    private lateinit var searchedUserObserver: Observer<Resource<User>>

    private val userName = "someUsername"
    private val user = User("userName", "name")

    @Before
    fun setUp() {
        underTest = SearchViewModel(userRepository, Schedulers.trampoline())
        searchedUserObserver = observeLiveData(underTest.searchedUser())
    }

    @Test
    fun willDisplayErrorWhenUserSearchFails() {
        whenUserSearchResultsIn(Single.error(Throwable()))

        underTest.onSearchClicked(userName)

        verify(searchedUserObserver).onChanged(Resource.LoadingResource())
        verify(searchedUserObserver).onChanged(Resource.ErrorResource(R.string.error_user_not_found))
    }

    @Test
    fun willDisplayUserWhenLoadedSuccessfully() {
        whenUserSearchResultsIn(Single.just(user))

        underTest.onSearchClicked(userName)

        verify(searchedUserObserver).onChanged(Resource.LoadingResource())
        verify(searchedUserObserver).onChanged(Resource.SuccessResource(user))
    }

    private fun whenUserSearchResultsIn(result: Single<User>) {
        given(userRepository.getUser(userName)).willReturn(result)
    }
}