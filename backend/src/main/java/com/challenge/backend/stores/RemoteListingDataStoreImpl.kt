package com.challenge.backend.stores

import com.challenge.backend.api.RedditService
import com.challenge.backend.utils.convert
import com.challenge.backend.utils.convertToListing
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditComments
import com.challenge.domain.entities.RedditListing
import com.challenge.domain.stores.listings.RemoteListingDataStore
import timber.log.Timber
import javax.inject.Inject

class RemoteListingDataStoreImpl @Inject constructor(
    private val redditApi : RedditService
) : RemoteListingDataStore {

    override suspend fun getSubredditListing(
        subReddit: String,
        listingType: ListingType,
        nextKey: String?
    ): RedditListing {
        return redditApi.getListing(
            subReddit,
            listingType.convert(),
            nextKey
        ).convertToListing()
    }

    override suspend fun getPostComments(postId: String, listingType: ListingType): RedditComments {
        val comments = mutableListOf<String?>()
        redditApi.getPostComments(
            postId
        ).map {
            it.data.children.map {
                val comment = it.data?.body
                if(comment.isNullOrEmpty().not()){
                    comments.add(comment)
                }
            }
        }
        return RedditComments( comments =  comments)
    }

}