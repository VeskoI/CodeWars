package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.SortOption
import com.veskoiliev.codewars.data.local.model.User
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_user.*
import javax.inject.Inject

class SearchUserActivity : AppCompatActivity(), SearchUserView {

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
        searchViewModel.searchedUser().observe(this, Observer { binder.bindSearchedUserResource(it) })
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

    override fun onUserSelected(user: User) {
        // TODO open the next screen. For now, just display a happy message
        Snackbar.make(search_user_root_view, "Wooohoo, user found: ${user.name}", Snackbar.LENGTH_LONG).show()
    }

    override fun toggleEmptyView(visible: Boolean) {
        search_empty_view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun hideUserHistoryList() {
        search_history_list.visibility = View.GONE
    }

    override fun displayError(errorMessage: Int) {
        Snackbar.make(search_user_root_view, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun toggleLoading(visible: Boolean) = if (visible) {
        search_loading_progress.show()
    } else {
        search_loading_progress.hide()
    }

    override fun showUserHistoryList(usersList: List<User>) {
        search_history_list.visibility = View.VISIBLE
        searchHistoryAdapter.items = usersList
        searchHistoryAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_item_sort_by_date -> {
                observeSearchHistoryOrderedBy(SortOption.SEARCH_TIME)
                return true
            }
            R.id.menu_item_sort_by_rank -> {
                observeSearchHistoryOrderedBy(SortOption.RANK)
                return true
            }
            R.id.menu_item_sort_by_leaderboard_score -> {
                observeSearchHistoryOrderedBy(SortOption.LEADER_BOARD_POSITION)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeSearchHistoryOrderedBy(sortOption: SortOption) {
        searchViewModel.searchHistorySortOption = sortOption
    }
}
