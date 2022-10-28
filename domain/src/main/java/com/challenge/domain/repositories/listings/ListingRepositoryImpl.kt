package com.challenge.domain.repositories.listings

import androidx.paging.*
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.orEmpty
import com.challenge.domain.repositories.ListingsPagerFactory
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.domain.stores.listings.RemoteListingDataStore
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val remoteListingDataStore : RemoteListingDataStore,
    private val localListingDataStore: LocalListingDataStore,
    private val listingsPagerFactory: ListingsPagerFactory
) : ListingsRepository {

    override suspend fun getListing(
        subReddit: String,
        listingType: ListingType
    ) : List<RedditPost> {

        val listings = remoteListingDataStore.getSubredditListing(
            subReddit,
            listingType
        )
        localListingDataStore.updateListings(
            listings.posts.firstOrNull()?.subReddit.orEmpty(),
            listings.posts,
            listings.remoteKeys
        )
        return listings.posts
    }

    override suspend fun getRedditListing(
        subReddit: String,
        listingType: ListingType
    ): Pager<Int, RedditPost> {
        return listingsPagerFactory.buildListingPager(
            subReddit,
            listingType
        )

    }

}