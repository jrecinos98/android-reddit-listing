package com.challenge.domain.entities

data class RedditListing(
    val subReddit : String,
    val upvotes : Int,
    val downvotes : Int,
    val rewardsReceived : Int,
    val op : String,
    val title : String,
    val thumbnail : String,
    val url : String,
    val createdAt : Int
)