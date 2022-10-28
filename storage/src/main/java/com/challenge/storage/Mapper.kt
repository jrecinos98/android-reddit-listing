package com.challenge.storage

import com.challenge.domain.entities.RedditPost
import com.challenge.domain.entities.RemoteKeys
import com.challenge.domain.orEmpty
import com.challenge.domain.orZero
import com.challenge.storage.entities.DBListing
import com.challenge.storage.entities.DBRemoteKeys

fun DBRemoteKeys?.convert() : RemoteKeys {
    return RemoteKeys(this?.nextKey?.orEmpty() , this?.prevKey?.orEmpty() )
}

fun RedditPost.convert() : DBListing {
    return DBListing(
        id,
        subReddit,
        upvotes,
        downvotes,
        rewardsReceived,
        uid,
        username,
        title,
        thumbnail,
        url,
        createdAt
    )
}

fun DBListing.convert() : RedditPost{
    return RedditPost(
        "","",0,
        0,0,"","",
        "", "", "", 0
    )
}