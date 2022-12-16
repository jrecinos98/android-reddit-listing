package com.challenge.tti.di

import com.challenge.domain.repositories.ListingsPagerFactory
import com.challenge.domain.repositories.ListingsRemoteMediator
import com.challenge.domain.repositories.listings.ListingRepositoryImpl
import com.challenge.domain.repositories.listings.ListingsRepository
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.domain.stores.listings.RemoteListingDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    @ViewModelScoped
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