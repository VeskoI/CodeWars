package com.veskoiliev.codewars.ui.search

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.veskoiliev.codewars.R

class SearchUserActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)

        initViewModel()
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.fetchSearchHistory()
    }
}
