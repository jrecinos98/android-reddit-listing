package com.challenge.domain.repositories.listings

import com.challenge.domain.entities.ListingType

interface ListingsRepository {

    suspend fun getListing(
        subReddit : String,
        listingType : ListingType
    )

}