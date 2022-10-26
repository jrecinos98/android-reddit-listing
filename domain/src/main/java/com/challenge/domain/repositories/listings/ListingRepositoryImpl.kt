package com.challenge.domain.repositories.listings

import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditListing
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.domain.stores.listings.RemoteListingDataStore
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val remoteListingDataStore : RemoteListingDataStore,
    private val localListingDataStore : LocalListingDataStore
) : ListingsRepository {
    override suspend fun getListing(
        subReddit: String,
        listingType: ListingType
    ) : List<RedditListing> {
        return remoteListingDataStore.getSubredditListing(
            subReddit,
            listingType
        )
    }
}