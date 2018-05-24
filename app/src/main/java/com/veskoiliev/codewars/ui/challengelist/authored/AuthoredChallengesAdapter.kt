package com.veskoiliev.codewars.ui.challengelist.authored

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge

class AuthoredChallengesAdapter(var items: List<AuthoredChallenge>): RecyclerView.Adapter<AuthoredChallengesViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthoredChallengesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_authored_challenge, parent, false)
        return AuthoredChallengesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthoredChallengesViewHolder, position: Int) {
        holder.bind(items[position])
    }
}