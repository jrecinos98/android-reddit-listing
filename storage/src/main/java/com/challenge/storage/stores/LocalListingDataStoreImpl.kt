package com.challenge.storage.stores

import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.storage.AppDatabase
import javax.inject.Inject

class LocalListingDataStoreImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : LocalListingDataStore {

    override suspend fun test() {
        TODO("Not yet implemented")
    }

}