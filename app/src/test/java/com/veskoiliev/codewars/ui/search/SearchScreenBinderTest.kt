package com.veskoiliev.codewars.ui.search

import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.testdata.TestUser
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchScreenBinderTest {

    @Mock
    private lateinit var view: SearchUserView

    private lateinit var underTest: SearchScreenBinder

    @Before
    fun setUp() {
        underTest = SearchScreenBinder(view)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
    }

    @Test
    fun willDisplayEmptyViewWhenBindingEmptyHistoryList() {
        underTest.bindSearchHistory(emptyList())

        verify(view).toggleEmptyView(true)
        verify(view).hideUserHistoryList()
    }

    @Test
    fun willDisplayUserHistoryListIfNotEmpty() {
        underTest.bindSearchHistory(TestUser.usersList)

        verify(view).toggleEmptyView(false)
        verify(view).showUserHistoryList(TestUser.usersList)
    }

    @Test
    fun willDisplayErrorIfInvalidResourceIsReceived() {
        underTest.bindSearchedUserResource(null)

        verify(view).toggleLoading(false)
        verify(view).displayError(R.string.error_generic_message)
    }

    @Test
    fun willDisplayLoadingIfLoadingResourceReceived() {
        underTest.bindSearchedUserResource(Resource.LoadingResource())

        verify(view).toggleLoading(true)
    }

    @Test
    fun willDisplayTheErrorFromErrorResource() {
        underTest.bindSearchedUserResource(Resource.ErrorResource(R.string.error_user_not_found))

        verify(view).toggleLoading(false)
        verify(view).displayError(R.string.error_user_not_found)
    }

    @Test
    fun willOpenUserDetailsPageWhenUserReceived() {
        underTest.bindSearchedUserResource(Resource.SuccessResource(TestUser.user))

        verify(view).toggleLoading(false)
        verify(view).onUserSelected(TestUser.user)
    }
}