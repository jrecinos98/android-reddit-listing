package com.challenge.domain.entities

data class RedditListing (
    val remoteKeys: RemoteKeys,
    val posts : List<RedditPost>
)