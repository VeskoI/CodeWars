package com.veskoiliev.codewars.ui.challengelist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import kotlinx.android.synthetic.main.list_item_completed_challenge.view.*

class CompletedChallengeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(challenge: CompletedChallenge?) {
        challenge?.let {
            view.completed_challenge_name.text = challenge.name
            view.completed_challenge_completion_date.text = challenge.completedAt
        }
    }
}