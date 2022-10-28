package com.challenge.domain.stores.listings

import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.PostComments
import com.challenge.domain.entities.RedditListing

interface RemoteListingDataStore {

    /**
     * Retrieves Subreddit listings from Reddit API
     */
    suspend fun getSubredditListing(
        subReddit : String,
        listingType: ListingType,
        nextKey: String? = null
    ) : RedditListing

    /**
     * Retrieves comments for the specified Reddit Post
     */
    suspend fun getPostComments(
        postId : String,
        listingType: ListingType
    ) : PostComments
}