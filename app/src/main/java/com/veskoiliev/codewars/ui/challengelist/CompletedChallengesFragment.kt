package com.veskoiliev.codewars.ui.challengelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import com.veskoiliev.codewars.ui.challengelist.ChallengeListActivity.Companion.EXTRA_USER
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_completed_challenges.*
import javax.inject.Inject

class CompletedChallengesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var completedChallengesViewModel: CompletedChallengesViewModel
    private lateinit var challengeListLayoutManager: RecyclerView.LayoutManager
    private val challengeListAdapter = CompletedChallengeListAdapter {
        completedChallengesViewModel.retry()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        completedChallengesViewModel.challenges.observe(this, Observer<PagedList<CompletedChallenge>> {
            challengeListAdapter.submitList(it)
        })
        completedChallengesViewModel.networkState.observe(this, Observer {
            challengeListAdapter.setNetworkState(it)
        })

        completedChallengesViewModel.init(getUserName())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_completed_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViewModel() {
        completedChallengesViewModel = ViewModelProviders.of(this, viewModelFactory).get(CompletedChallengesViewModel::class.java)
    }

    private fun initViews() {
        challengeListLayoutManager = LinearLayoutManager(context)
        challenge_list.layoutManager = challengeListLayoutManager
        challenge_list.adapter = challengeListAdapter
        challenge_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun getUserName(): String {
        return requireActivity().intent.getParcelableExtra<User>(EXTRA_USER)?.username
                ?: throw IllegalStateException("Cannot initialize CompletedChallengesFragment without username")
    }
}