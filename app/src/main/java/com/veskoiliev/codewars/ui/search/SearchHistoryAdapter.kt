package com.veskoiliev.codewars.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.User

class SearchHistoryAdapter(var items: List<User>, val itemClickListener: UserClickListener): RecyclerView.Adapter<SearchHistoryViewHolder>() {


    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_search_history, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }
}