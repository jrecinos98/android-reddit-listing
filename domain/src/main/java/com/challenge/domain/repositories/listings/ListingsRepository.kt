package com.challenge.domain.repositories.listings

import androidx.paging.Pager
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.PostComments
import com.challenge.domain.entities.RedditPost

interface ListingsRepository {

    suspend fun getRedditListing(
        subReddit: String,
        listingType: ListingType
    ) : Pager<Int, RedditPost>

    suspend fun getPostComments(
        postId : String,
        listingType: ListingType
    ) : PostComments

}