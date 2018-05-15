package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.User
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_user.*
import javax.inject.Inject

class SearchUserActivity : AppCompatActivity(), SearchUserView, UserClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var binder: SearchScreenBinder

    private lateinit var searchViewModel: SearchViewModel

    private val searchHistoryAdapter = SearchHistoryAdapter(emptyList(), this)
    private val searchHistoryLayoutManager = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search_user)

        initViewModel()
        initViews()
        searchViewModel.searchedUser().observe(this, Observer { it?.let { handleSearchedUserResource(it) } })
        searchViewModel.searchHistory().observe(this, Observer { it?.let { binder.bindSearchHistory(it) } })
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    private fun initViews() {
        search_btn.setOnClickListener { searchViewModel.onSearchClicked(search_username_field.text.toString()) }

        search_history_list.layoutManager = searchHistoryLayoutManager
        search_history_list.adapter = searchHistoryAdapter
        search_history_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
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

    private fun openUserDetailsScreen(user: User) {
        // TODO open the next screen. For now, just display a happy message
        Snackbar.make(search_user_root_view, "Wooohoo, user found: ${user.name}", Snackbar.LENGTH_LONG).show()
    }

    override fun onUserSelected(user: User) = openUserDetailsScreen(user)

    override fun toggleEmptyView(visible: Boolean) {
        search_empty_view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun hideUserHistoryList() {
        search_history_list.visibility = View.GONE
    }

    override fun showUserHistoryList(usersList: List<User>) {
        search_history_list.visibility = View.VISIBLE
        searchHistoryAdapter.items = usersList
        searchHistoryAdapter.notifyDataSetChanged()
    }
}
