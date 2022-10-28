package com.challenge.domain.stores.listings

import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditComments
import com.challenge.domain.entities.RedditListing
import com.challenge.domain.entities.RedditPost

interface RemoteListingDataStore {

    suspend fun getSubredditListing(
        subReddit : String,
        listingType: ListingType,
        nextKey: String? = null
    ) : RedditListing

    suspend fun getPostComments(
        postId : String,
        listingType: ListingType
    ) : RedditComments
}