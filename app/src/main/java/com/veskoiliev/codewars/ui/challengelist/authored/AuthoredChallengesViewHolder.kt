package com.veskoiliev.codewars.ui.challengelist.authored

import android.support.v7.widget.RecyclerView
import android.view.View
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import kotlinx.android.synthetic.main.list_item_authored_challenge.view.*

class AuthoredChallengesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(challenge: AuthoredChallenge) {
        view.authored_challenge_name.text = challenge.name
        view.authored_challenge_description.text = challenge.description
    }
}