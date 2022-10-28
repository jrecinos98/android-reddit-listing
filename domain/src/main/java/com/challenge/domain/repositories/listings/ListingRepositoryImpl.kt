package com.challenge.domain.repositories.listings

import androidx.paging.*
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.PostComments
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.orEmpty
import com.challenge.domain.repositories.ListingsPagerFactory
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.domain.stores.listings.RemoteListingDataStore
import javax.inject.Inject

/**
 * Repository to handle getting Reddit Listings and Comments use cases.
 */
class ListingRepositoryImpl @Inject constructor(
    private val remoteListingDataStore : RemoteListingDataStore,
    private val listingsPagerFactory: ListingsPagerFactory
) : ListingsRepository {

    override suspend fun getRedditListing(
        subReddit: String,
        listingType: ListingType
    ): Pager<Int, RedditPost> {
        return listingsPagerFactory.buildListingPager(
            subReddit,
            listingType
        )
    }

    override suspend fun getPostComments(
        postId: String,
        listingType: ListingType
    ): PostComments {
        return remoteListingDataStore.getPostComments(
            postId,
            listingType
        )
    }

}