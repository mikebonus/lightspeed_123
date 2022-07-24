package com.lightspeed.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lightspeed.domain.model.Lightspeed
import com.lightspeed.lightspeedproject.databinding.NewsItemBinding
import com.lightspeed.presentation.ui.DetailActivity

class LightspeedAdapter : ListAdapter<Lightspeed,
        LightspeedAdapter.LightspeedViewHolder>(LightspeedComparators()) {

    val TAG = "TAG"

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

        holder.itemView.setOnClickListener {
            val intentData = Intent(holder.itemView.context, DetailActivity::class.java)
            intentData.putExtra("author", currentItem?.author)
            intentData.putExtra("download_url", currentItem?.download_url)
            ContextCompat.startActivity(holder.itemView.context, intentData, null)
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
