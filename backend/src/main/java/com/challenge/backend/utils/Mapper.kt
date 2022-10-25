package com.challenge.backend.utils

import com.challenge.backend.Constants
import com.challenge.domain.entities.ListingType

fun ListingType.convert() : String {
    return when(this){
        ListingType.HOT -> Constants.Listings.HOT
        ListingType.TOP -> Constants.Listings.TOP
        ListingType.NEW -> Constants.Listings.NEW
        ListingType.RANDOM -> Constants.Listings.RANDOM
    }
}