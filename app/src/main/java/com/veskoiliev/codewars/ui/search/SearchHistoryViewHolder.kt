package com.veskoiliev.codewars.ui.search

import android.support.v7.widget.RecyclerView
import android.view.View
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User
import kotlinx.android.synthetic.main.list_item_search_history.view.*

class SearchHistoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(user: User, clickListener: UserSelectedListener) {
        view.setOnClickListener { clickListener.onUserSelected(user) }
        view.search_history_item_name.text = user.name
        view.search_history_item_rank.text = view.context.getString(R.string.formatted_rank, user.rank)
        view.search_history_item_best_language.text = view.context.getString(R.string.formatted_best_language, user.bestLanguage, user.bestLanguagePoints)
    }
}