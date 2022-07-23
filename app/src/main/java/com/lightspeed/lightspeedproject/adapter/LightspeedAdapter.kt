package com.lightspeed.lightspeedproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lightspeed.lightspeedproject.data.Lightspeed
import com.lightspeed.lightspeedproject.databinding.NewsItemBinding

class LightspeedAdapter : ListAdapter<Lightspeed,
        LightspeedAdapter.LightspeedViewHolder>(LightspeedComparators()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LightspeedViewHolder {
        val binding = NewsItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return LightspeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LightspeedViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class LightspeedViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lightspeed: Lightspeed) {
            binding.apply {

                // IMAGES
                Glide.with(itemView)
                    .load(lightspeed.download_url)
                    .into(imageViewLogo)

                // AUTHORS
                textViewAuthor.text = lightspeed.author
            }
        }
    }

    class LightspeedComparators : DiffUtil.ItemCallback<Lightspeed>() {
        override fun areItemsTheSame(oldItem: Lightspeed, newItem: Lightspeed) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Lightspeed, newItem: Lightspeed) =
            oldItem == newItem
    }

}
