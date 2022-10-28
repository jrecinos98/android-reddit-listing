package com.challenge.domain.entities

data class RedditPost(
    val id : String,
    val subReddit : String,
    val upvotes : Int,
    val downvotes : Int,
    val rewardsReceived : Int,
    val uid : String,
    val username : String,
    val title : String,
    val thumbnail : String,
    val url : String,
    val createdAt : Int
)