package com.challenge.domain.repositories.listings

import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditListing

interface ListingsRepository {

    suspend fun getListing(
        subReddit : String,
        listingType : ListingType
    ) : List<RedditListing>

}