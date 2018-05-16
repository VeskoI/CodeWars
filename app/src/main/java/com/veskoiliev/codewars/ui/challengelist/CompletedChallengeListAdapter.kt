package com.veskoiliev.codewars.ui.challengelist

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.NetworkState
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge

class CompletedChallengeListAdapter(
        private val retryCallback: () -> Unit
): PagedListAdapter<CompletedChallenge, RecyclerView.ViewHolder>(COMPLETED_CHALLENGE_COMPARATOR) {
    private var networkState: NetworkState? = null

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.list_item_network_state
        } else {
            R.layout.list_item_completed_challenge
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            R.layout.list_item_completed_challenge -> {
                val view = layoutInflater.inflate(R.layout.list_item_completed_challenge, parent, false)
                CompletedChallengeViewHolder(view)
            }
            R.layout.list_item_network_state -> {
                val view = layoutInflater.inflate(R.layout.list_item_network_state, parent, false)
                NetworkStateItemViewHolder(view, retryCallback)
            }
            else -> throw IllegalArgumentException("Unknown item view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            R.layout.list_item_completed_challenge -> (holder as CompletedChallengeViewHolder).bind(getItem(position))
            R.layout.list_item_network_state -> (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(networkState: NetworkState?) {
        val previousState = this.networkState
        val previousHasExtraRow = hasExtraRow()

        this.networkState = networkState
        val hasExtraRow = hasExtraRow()

        if (previousHasExtraRow != hasExtraRow) {
            if (previousHasExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != networkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        val COMPLETED_CHALLENGE_COMPARATOR = object: DiffUtil.ItemCallback<CompletedChallenge>() {
            override fun areContentsTheSame(oldItem: CompletedChallenge, newItem: CompletedChallenge): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: CompletedChallenge, newItem: CompletedChallenge): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}