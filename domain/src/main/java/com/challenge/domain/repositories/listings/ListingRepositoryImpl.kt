package com.challenge.domain.repositories.listings

import com.challenge.domain.stores.listings.RemoteListingDataStore
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val remoteListingDataStore: RemoteListingDataStore
) : ListingsRepository {
    override suspend fun printTest() {
        remoteListingDataStore.printTest()
    }
}