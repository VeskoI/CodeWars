package com.veskoiliev.codewars.ui.challengelist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User
import kotlinx.android.synthetic.main.activity_challenge_list.*

class ChallengeListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "intent_extra_user"

        fun newIntent(context: Context, user: User) =
                Intent(context, ChallengeListActivity::class.java).apply {
                    putExtra(EXTRA_USER, user)
                }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_challenge_list)

        initViews()
    }

    private fun initViews() {
        title = getUser().name
        challenge_list_bottom_navigation.setupWithNavController(findNavController(R.id.challenges_nav_host_fragment))
    }

    private fun getUser(): User = intent.getParcelableExtra(EXTRA_USER)
            ?: throw IllegalStateException("Cannot start ChallengeActivity without user")
}
