package com.challenge.tti.ui.main.listings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.domain.entities.RedditListing
import com.challenge.tti.R
import com.challenge.tti.databinding.ItemListingBinding

class ListingsAdapter : ListAdapter<RedditListing, RedditListingViewHolder>(diff) {

    companion object{
        val diff = object : DiffUtil.ItemCallback<RedditListing>(){
            override fun areItemsTheSame(oldItem: RedditListing, newItem: RedditListing): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RedditListing,
                newItem: RedditListing
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    var onItemClick : ((RedditListing) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditListingViewHolder {
        return RedditListingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_listing, parent, false)
        ).apply {
            itemView.setOnClickListener {
                onItemClick?.invoke(currentList[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: RedditListingViewHolder, position: Int) {
        val item = currentList[position]
        val binding = ItemListingBinding.bind(holder.itemView)
        with(holder.itemView){
            binding.title.text = item.title
            Glide.with(context).load(item.thumbnail).into(binding.image)
        }
    }
}

class RedditListingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)