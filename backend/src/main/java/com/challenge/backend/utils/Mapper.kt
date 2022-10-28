package com.challenge.backend.utils

import com.challenge.backend.Constants
import com.challenge.backend.api.model.ListingResponse
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditListing
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.entities.RemoteKeys
import com.challenge.domain.orZero

fun ListingType.convert() : String {
    return when(this){
        ListingType.HOT -> Constants.Listings.HOT
        ListingType.TOP -> Constants.Listings.TOP
        ListingType.NEW -> Constants.Listings.NEW
        ListingType.RANDOM -> Constants.Listings.RANDOM
    }
}

fun ListingResponse.convert() : List<RedditPost> {
    return data.children.map {
        with(it.data!!){
            val thumbnail = when(this.thumbnail){
                null, "", "self", "default" -> Constants.IMG_DEFAULT
                else -> this.thumbnail
            }
            RedditPost(
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

fun ListingResponse.convertToListing() : RedditListing {
    return RedditListing(
        remoteKeys = RemoteKeys(data.after, data.before),
        posts = data.children.map {
            with(it.data!!){
                val thumbnail = when(this.thumbnail){
                    null, "", "self", "default" -> Constants.IMG_DEFAULT
                    else -> this.thumbnail
                }
                RedditPost(
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
    )
}