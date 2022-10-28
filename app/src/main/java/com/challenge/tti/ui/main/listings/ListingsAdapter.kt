package com.challenge.tti.ui.main.listings

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.domain.entities.RedditPost
import com.challenge.tti.R
import com.challenge.tti.databinding.ItemListingBinding
import com.challenge.tti.toSpannedHtml

class ListingsAdapter : PagingDataAdapter<RedditPost, RedditListingViewHolder>(diff) {

    companion object{
        val diff = object : DiffUtil.ItemCallback<RedditPost>(){
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.id == newItem.id  && oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(
                oldItem: RedditPost,
                newItem: RedditPost
            ): Boolean {
                return oldItem.thumbnail == newItem.thumbnail && oldItem.title == newItem.title
            }

        }
    }

    var onItemClick : ((RedditPost) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditListingViewHolder {
        return RedditListingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_listing, parent, false)
        ).apply {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick?.invoke(it)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RedditListingViewHolder, position: Int) {
        getItem(position)?.let { item ->
            val binding = ItemListingBinding.bind(holder.itemView)
            with(holder.itemView){
                binding.title.text = item.title.toSpannedHtml()
                Glide.with(context).load(item.thumbnail).into(binding.image)
            }
        }
    }
}

class RedditListingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)