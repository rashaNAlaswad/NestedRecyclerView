package com.rns.nestedrecyclerview.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rns.nestedrecyclerview.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rns.nestedrecyclerview.data.domain.Channel
import com.rns.nestedrecyclerview.databinding.ItemSubscriptionsBinding

class ChannelAdapter(private val channelList: List<Channel>) :
    RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    class ChannelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemSubscriptionsBinding.bind(view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChannelViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_subscriptions, viewGroup, false)
        return ChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        Glide.with(holder.binding.root).load(channelList[position].image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.image)
    }

    override fun getItemCount() = channelList.size
}