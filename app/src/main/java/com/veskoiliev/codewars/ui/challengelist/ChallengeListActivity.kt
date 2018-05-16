package com.veskoiliev.codewars.ui.challengelist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User
import kotlinx.android.synthetic.main.activity_challenge_list.*

class ChallengeListActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_USER = "intent_extra_user"

        fun newIntent(context: Context, user: User) =
                Intent(context, ChallengeListActivity::class.java).apply {
                    putExtra(EXTRA_USER, user)
                }
    }
    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_completed_challenges_item -> {
                challenge_list_message.setText(R.string.menu_text_completed_challenges)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_authored_challenges_item -> {
                challenge_list_message.setText(R.string.menu_text_authored_challenges)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_list)

        challenge_list_bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }
}
