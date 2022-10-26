package com.challenge.backend.utils

import com.challenge.backend.Constants
import com.challenge.backend.api.model.ListingResponse
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditListing

fun ListingType.convert() : String {
    return when(this){
        ListingType.HOT -> Constants.Listings.HOT
        ListingType.TOP -> Constants.Listings.TOP
        ListingType.NEW -> Constants.Listings.NEW
        ListingType.RANDOM -> Constants.Listings.RANDOM
    }
}

fun String?.orEmpty() : String {
    return this ?: ""
}

fun Int?.orZero() : Int {
    return this ?: 0
}

fun ListingResponse.convert() : List<RedditListing> {
    return data.children.map {
        with(it.data!!){
            val thumbnail = when(this.thumbnail){
                null, "", "self", "default" -> Constants.IMG_DEFAULT
                else -> this.thumbnail
            }
            RedditListing(
                this.id,
                this.subReddit.orEmpty(),
                this.upvotes.orZero(),
                this.downvotes.orZero(),
                this.rewardsReceived.orZero(),
                this.uid.orEmpty(),
                this.username.orEmpty(),
                this.title.orEmpty(),
                thumbnail,
                this.url.orEmpty(),
                this.createdAtUtc.orZero()
            )
        }
    }
}