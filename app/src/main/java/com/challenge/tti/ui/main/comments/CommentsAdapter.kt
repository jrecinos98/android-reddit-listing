package com.challenge.tti.ui.main.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.challenge.domain.entities.Comment
import com.challenge.tti.R
import com.challenge.tti.databinding.ItemCommentBinding

class CommentsAdapter : ListAdapter<Comment, RedditCommentViewHolder>(diff) {

    companion object{
        val diff = object : DiffUtil.ItemCallback<Comment>(){
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Comment,
                newItem: Comment
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditCommentViewHolder {
        return RedditCommentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RedditCommentViewHolder, position: Int) {
        getItem(position)?.let { item ->
            val binding = ItemCommentBinding.bind(holder.itemView)
            binding.commentView.text = item.text
        }
    }
}


class RedditCommentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
