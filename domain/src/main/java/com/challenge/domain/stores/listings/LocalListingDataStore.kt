package com.challenge.domain.stores.listings

import androidx.paging.PagingSource
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.entities.RemoteKeys

interface LocalListingDataStore {

    suspend fun updatePagingKeys(
        subReddit: String,
        previousKey : String?,
        nextKey : String?
    )

    suspend fun updateListings(
        subReddit: String,
        listings : List<RedditPost>,
        remoteKeys: RemoteKeys
    )

    suspend fun getPagingKeys(subReddit: String) : RemoteKeys

    fun getListings(subReddit : String) : PagingSource<Int, RedditPost>

    suspend fun clearListings(subReddit: String)
}