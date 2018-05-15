package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.User
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_user.*
import javax.inject.Inject

class SearchUserActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search_user)

        initViewModel()
        initViews()
        searchViewModel.searchedUser().observe(this, Observer { it?.let { handleSearchedUserResource(it) } })
    }

    private fun handleSearchedUserResource(userResource: Resource<User>) {
        when (userResource) {
            is Resource.LoadingResource -> {
                search_loading_progress.show()
            }
            is Resource.ErrorResource -> {
                search_loading_progress.hide()
                Snackbar.make(search_user_root_view, userResource.errorMessage, Snackbar.LENGTH_LONG).show()
            }
            is Resource.SuccessResource -> {
                search_loading_progress.hide()
                openUserDetailsScreen(userResource.data!!)
            }
        }
    }

    private fun openUserDetailsScreen(data: User) {
        // TODO open the next screen. For now, just display a happy message
        Snackbar.make(search_user_root_view, "Wooohoo, user found", Snackbar.LENGTH_LONG).show()
    }

    private fun initViews() {
        search_btn.setOnClickListener { searchViewModel.onSearchClicked(search_username_field.text.toString()) }
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        searchViewModel.fetchSearchHistory()
    }
}
