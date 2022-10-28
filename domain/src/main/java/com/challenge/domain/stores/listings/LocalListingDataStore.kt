package com.challenge.domain.stores.listings

import androidx.paging.PagingSource
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.entities.RemoteKeys

interface LocalListingDataStore {

    /**
     * Updates the Remote Paging Keys for a given subReddit in local database
     */
    suspend fun updatePagingKeys(
        subReddit: String,
        previousKey : String?,
        nextKey : String?
    )

    /**
     * Updates listings for specified subReddit in local database
     */
    suspend fun updateListings(
        subReddit: String,
        listings : List<RedditPost>,
        remoteKeys: RemoteKeys
    )

    /**
     * Retrieves Remote Paging keys for given subreddit
     */
    suspend fun getPagingKeys(subReddit: String) : RemoteKeys

    /**
     * Retrieves PagingSource for listings in given subReddit.
     * Note: PagingSource is managed automatically by Room.
     */
    fun getListings(subReddit : String) : PagingSource<Int, RedditPost>

    /**
     * Clears all the listings for the given subReddit along with the RemoteKeys
     */
    suspend fun clearListings(subReddit: String)
}