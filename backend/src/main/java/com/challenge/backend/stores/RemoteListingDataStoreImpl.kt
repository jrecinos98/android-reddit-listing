package com.challenge.backend.stores

import com.challenge.backend.api.RedditService
import com.challenge.backend.utils.convert
import com.challenge.backend.utils.convertToListing
import com.challenge.domain.entities.Comment
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.PostComments
import com.challenge.domain.entities.RedditListing
import com.challenge.domain.stores.listings.RemoteListingDataStore
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

    override suspend fun getPostComments(postId: String, listingType: ListingType): PostComments {
        val comments = mutableListOf<Comment>()
        redditApi.getPostComments(
            postId
        ).map {
            it.data.children.map {
                val comment = it.data?.body
                if(!comment.isNullOrEmpty()){
                    comments.add(
                        Comment(text = comment)
                    )
                }
            }
        }
        return PostComments( comments =  comments)
    }

}