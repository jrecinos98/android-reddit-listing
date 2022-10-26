package com.challenge.backend.stores

import com.challenge.backend.api.RedditService
import com.challenge.backend.utils.convert
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditListing
import com.challenge.domain.stores.listings.RemoteListingDataStore
import timber.log.Timber
import javax.inject.Inject

class RemoteListingDataStoreImpl @Inject constructor(
    private val redditApi : RedditService
) : RemoteListingDataStore {


    override suspend fun getSubredditListing(
        subReddit: String,
        listingType: ListingType
    ) : List<RedditListing> {
        /*Timber.d(
            redditApi.getListing(
                subReddit,
                listingType.convert()
            ).toString()
        )*/
        return redditApi.getListing(
            subReddit,
            listingType.convert()
        ).convert()
    }

}