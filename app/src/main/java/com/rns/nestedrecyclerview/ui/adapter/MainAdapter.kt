package com.rns.nestedrecyclerview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rns.nestedrecyclerview.R
import com.rns.nestedrecyclerview.data.MainItem
import com.rns.nestedrecyclerview.data.domain.Channel
import com.rns.nestedrecyclerview.data.domain.Podcast
import com.rns.nestedrecyclerview.databinding.ItemChannelsListBinding
import com.rns.nestedrecyclerview.databinding.ItemPodcastBinding
import com.rns.nestedrecyclerview.data.enums.MainItemType

class MainAdapter(private var list: List<MainItem<Any>>) :
    RecyclerView.Adapter<MainAdapter.BaseViewHolder>() {

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class PodcastViewHolder(view: View) : BaseViewHolder(view) {
        val binding = ItemPodcastBinding.bind(view)
    }

    class ChannelViewHolder(view: View) : BaseViewHolder(view) {
        val binding = ItemChannelsListBinding.bind(view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_CHANNEL -> {
                ChannelViewHolder(
                    LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.item_channels_list, viewGroup, false)
                )
            }
            VIEW_TYPE_PODCAST -> {
                PodcastViewHolder(
                    LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.item_podcast, viewGroup, false)
                )
            }
            else -> throw Exception("UNKNOWN VIEW TYPE")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            MainItemType.TYPE_CHANNEL -> VIEW_TYPE_CHANNEL
            MainItemType.TYPE_PODCAST -> VIEW_TYPE_PODCAST
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ChannelViewHolder -> bindChannel(holder, position)
            is PodcastViewHolder -> bindPodcast(holder, position)
        }
    }

    private fun bindChannel(holder: ChannelViewHolder, position: Int) {
        val channels = list[position].item as List<Channel>
        val adapter = ChannelAdapter(channels)
        holder.binding.recyclerview.adapter = adapter
    }

    private fun bindPodcast(holder: PodcastViewHolder, position: Int) {
        var currentPodcast = list[position].item
        currentPodcast = currentPodcast as Podcast
        holder.binding.apply {
            textChannelName.text = currentPodcast.channel_name
            textEpisodeName.text = currentPodcast.episode_name
            textDescription.text = currentPodcast.description
            Glide.with(holder.binding.root).load(currentPodcast.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.binding.imageView)
        }
    }

    override fun getItemCount(): Int = list.size

    companion object {
        private const val VIEW_TYPE_CHANNEL = 1
        private const val VIEW_TYPE_PODCAST = 2
    }
}