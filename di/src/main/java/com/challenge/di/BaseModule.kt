package com.challenge.di

import android.app.Application
import com.challenge.backend.di.BackendComponent
import com.challenge.backend.di.DaggerBackendComponent
import com.challenge.domain.repositories.ListingsPagerFactory
import com.challenge.domain.repositories.ListingsRemoteMediator
import com.challenge.domain.repositories.listings.ListingRepositoryImpl
import com.challenge.domain.repositories.listings.ListingsRepository
import com.challenge.storage.di.DaggerStorageComponent

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule (private val app : Application) {

    /**
     * Instance of BackendComponent that exposes all necessary
     * objects from the backend module
     */
    private val backendComponent : BackendComponent by lazy {
        DaggerBackendComponent.builder().build()
    }

    /**
     * Instance of StorageComponent that exposes all necessary
     * objects from the storage module
     */
    private val storageComponent by lazy {
        DaggerStorageComponent.factory().create(app)
    }

    @Singleton
    @Provides
    fun providesRedditRepository() : ListingsRepository {
        val pagerFactory = ListingsPagerFactory.create()
            .addMediator(
                ListingsRemoteMediator(
                    storageComponent.getLocalDataStore(),
                    backendComponent.getRemoteDataStore()
                )
            )
            .addLocalDataStore(storageComponent.getLocalDataStore())
        return ListingRepositoryImpl(
            backendComponent.getRemoteDataStore(),
            storageComponent.getLocalDataStore(),
            pagerFactory
        )
    }
}