package com.challenge.di

import android.app.Application
import android.content.Context
import com.challenge.backend.di.BackendComponent
import com.challenge.backend.di.DaggerBackendComponent
import com.challenge.domain.repositories.ListingsPagerFactory
import com.challenge.domain.repositories.ListingsRemoteMediator
import com.challenge.domain.repositories.listings.ListingRepositoryImpl
import com.challenge.domain.repositories.listings.ListingsRepository
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.domain.stores.listings.RemoteListingDataStore
import com.challenge.storage.di.StorageComponent

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class BaseModule {

    @Provides
    fun providesLocalDataStore(storageComponent: StorageComponent) : LocalListingDataStore {
        return storageComponent.getLocalDataStore()
    }
    @Provides
    fun providesRemoteDataStore(remoteDataStore: BackendComponent) : RemoteListingDataStore {
        return remoteDataStore.getRemoteDataStore()
    }

    @Singleton
    @Provides
    fun providesRedditRepository(
        localListingDataStore: LocalListingDataStore,
        remoteListingDataStore: RemoteListingDataStore
    ) : ListingsRepository {
        val pagerFactory = ListingsPagerFactory.create()
            .addMediator(
                ListingsRemoteMediator(localListingDataStore,remoteListingDataStore)
            )
            .addLocalDataStore(localListingDataStore)
        return ListingRepositoryImpl(remoteListingDataStore, pagerFactory)
    }
}