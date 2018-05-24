package com.veskoiliev.codewars.ui.challengelist.authored

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.ext.toggleVisibility
import com.veskoiliev.codewars.ui.challengelist.ChallengeListActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_authored_challenges.*
import javax.inject.Inject

class AuthoredChallengesFragment : Fragment(), AuthoredChallengesView {

    @Inject
    lateinit var viewModelFactor: ViewModelProvider.Factory

    @Inject
    lateinit var binder: AuthoredChallengesScreenBinder

    private lateinit var viewModel: AuthoredChallengesViewModel

    private val authoredChallengesAdapter = AuthoredChallengesAdapter(emptyList())

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        viewModel.authoredChallenges().observe(this, Observer { binder.bindChallengesResource(it) })
        viewModel.init(getUserName())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authored_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun toggleLoading(visible: Boolean) {
        authored_challenges_loading_bar.toggleVisibility(visible)
    }

    override fun toggleEmptyView(visible: Boolean) {
        authored_challenges_message_view.toggleVisibility(visible)
        authored_challenges_message_view.text = getString(R.string.authored_challenges_empty_text)
    }

    override fun displayError(@StringRes errorMessage: Int) {
        authored_challenges_message_view.toggleVisibility(true)
        authored_challenges_message_view.text = getString(errorMessage)
    }

    override fun showChallengesList(challenges: List<AuthoredChallenge>) {
        authored_challenges_list.toggleVisibility(true)
        authoredChallengesAdapter.items = challenges
        authoredChallengesAdapter.notifyDataSetChanged()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactor).get(AuthoredChallengesViewModel::class.java)
    }

    private fun initViews() {
        authored_challenges_list.layoutManager = LinearLayoutManager(context)
        authored_challenges_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        authored_challenges_list.adapter = authoredChallengesAdapter
    }

    private fun getUserName(): String {
        return requireActivity().intent.getParcelableExtra<User>(ChallengeListActivity.EXTRA_USER)?.username
                ?: throw IllegalStateException("Cannot initialize AuthoredChallengesFragment without username")
    }
}
