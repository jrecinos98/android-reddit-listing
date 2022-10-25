package com.challenge.di

import android.app.Application
import com.challenge.backend.di.BackendComponent
import com.challenge.backend.di.DaggerBackendComponent
import com.challenge.domain.repositories.listings.ListingRepositoryImpl
import com.challenge.domain.repositories.listings.ListingsRepository
import com.challenge.storage.di.DaggerStorageComponent

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule (private val app : Application) {

    private val backendComponent : BackendComponent by lazy {
        DaggerBackendComponent.builder().build()
    }

    private val storageComponent by lazy {
        DaggerStorageComponent.factory().create(app)
    }

    @Singleton
    @Provides
    fun providesRedditRepository() : ListingsRepository {
        return ListingRepositoryImpl(backendComponent.getRemoteDataStore())
    }

    @Singleton
    @Provides
    fun providesDatabaseModule() : String {
        val db =  storageComponent.providesDatabase()
        return "Work"
    }

}