package com.challenge.domain.stores.listings

import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditListing

interface RemoteListingDataStore {

    suspend fun getSubredditListing(
        subReddit : String,
        listingType: ListingType
    ) : List<RedditListing>

}