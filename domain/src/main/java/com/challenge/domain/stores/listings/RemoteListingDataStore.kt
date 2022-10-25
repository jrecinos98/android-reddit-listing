package com.challenge.domain.stores.listings

import com.challenge.domain.entities.ListingType

interface RemoteListingDataStore {

    suspend fun getSubredditListing(
        subReddit : String,
        listingType: ListingType
    )

}