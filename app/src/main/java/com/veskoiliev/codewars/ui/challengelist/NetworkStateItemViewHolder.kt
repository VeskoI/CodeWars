package com.veskoiliev.codewars.ui.challengelist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.veskoiliev.codewars.data.NetworkState
import com.veskoiliev.codewars.data.Status
import kotlinx.android.synthetic.main.list_item_network_state.view.*

class NetworkStateItemViewHolder(private val view: View, private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {

    fun bind(networkState: NetworkState?) {
        view.network_state_item_error_msg.visibility = toVisbility(networkState?.status == Status.RUNNING)
        view.network_state_item_retry_button.visibility = toVisbility(networkState?.status == Status.FAILED)
        view.network_state_item_retry_button.setOnClickListener { retryCallback() }
        view.network_state_item_error_msg.visibility = toVisbility(networkState?.msg != null)
        view.network_state_item_error_msg.text = networkState?.msg
    }

    private fun toVisbility(constraint : Boolean): Int {
        return if (constraint) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}