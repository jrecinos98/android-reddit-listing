package com.challenge.storage.stores

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.entities.RemoteKeys
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.storage.AppDatabase
import com.challenge.storage.convert
import com.challenge.storage.entities.DBRemoteKeys
import javax.inject.Inject

class LocalListingDataStoreImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : LocalListingDataStore {

    override suspend fun updatePagingKeys(subReddit: String, previousKey: String?, nextKey: String?) {
        appDatabase.keysDao().insert(
            DBRemoteKeys(id = subReddit, prevKey = previousKey, nextKey = nextKey)
        )
    }

    override suspend fun updateListings(
        subReddit: String,
        listings: List<RedditPost>,
        remoteKeys: RemoteKeys
    ) {
        appDatabase.withTransaction {
            appDatabase.listingsDao().updateListings(listings.map { it.convert() })
            appDatabase.keysDao().insert(
                DBRemoteKeys(id = subReddit, prevKey = remoteKeys.prevKey, nextKey = remoteKeys.nextKey)
            )
        }

    }

    override suspend fun getPagingKeys(subReddit: String): RemoteKeys {
        return appDatabase.keysDao().getRemoteKeys(subReddit).convert()
    }

     override fun getListings(subReddit : String): PagingSource<Int, RedditPost> {
         return appDatabase.listingsDao().getListings(subReddit)
     }

    override suspend fun clearListings(subReddit : String) {
        appDatabase.withTransaction {
            appDatabase.keysDao().clearRemoteKeys(subReddit)
            appDatabase.listingsDao().clearListings(subReddit)
        }
    }
}