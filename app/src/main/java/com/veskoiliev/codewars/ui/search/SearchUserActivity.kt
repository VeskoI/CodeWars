package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.veskoiliev.codewars.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_user.*
import javax.inject.Inject

class SearchUserActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search_user)

        initViewModel()
        initViews()
    }

    private fun initViews() {
        search_btn.setOnClickListener { searchViewModel.onSearchClicked(search_username_field.text.toString()) }
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        searchViewModel.fetchSearchHistory()
    }
}
