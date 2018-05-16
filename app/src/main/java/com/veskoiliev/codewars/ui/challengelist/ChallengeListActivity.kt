package com.veskoiliev.codewars.ui.challengelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_challenge_list.*
import javax.inject.Inject

class ChallengeListActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_USER = "intent_extra_user"

        fun newIntent(context: Context, user: User) =
                Intent(context, ChallengeListActivity::class.java).apply {
                    putExtra(EXTRA_USER, user)
                }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var completedChallengesViewModel: CompletedChallengesViewModel
    private val challengeListLayoutManager = LinearLayoutManager(this)
    private val challengeListAdapter = CompletedChallengeListAdapter {
        completedChallengesViewModel.retry()
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_completed_challenges_item -> {
                // TODO not implemented
                Toast.makeText(this, R.string.menu_text_completed_challenges, Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_authored_challenges_item -> {
                // TODO not implemented
                Toast.makeText(this, R.string.menu_text_authored_challenges, Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_challenge_list)

        initViewModel()
        initViews()

        completedChallengesViewModel.challenges.observe(this, Observer<PagedList<CompletedChallenge>> {
            challengeListAdapter.submitList(it)
        })
        completedChallengesViewModel.networkState.observe(this, Observer {
            challengeListAdapter.setNetworkState(it)
        })

        completedChallengesViewModel.init(getUserName())
    }

    private fun getUserName() = intent.getParcelableExtra<User>(EXTRA_USER)?.username
            ?: throw IllegalStateException("Cannot start ChallengeActivity without user")

    private fun initViewModel() {
        completedChallengesViewModel = ViewModelProviders.of(this, viewModelFactory).get(CompletedChallengesViewModel::class.java)
    }

    private fun initViews() {
        challenge_list_bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        challenge_list.layoutManager = challengeListLayoutManager
        challenge_list.adapter = challengeListAdapter
        challenge_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}
