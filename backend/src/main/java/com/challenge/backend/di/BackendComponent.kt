package com.challenge.backend.di

import com.challenge.domain.stores.listings.RemoteListingDataStore
import dagger.Component
import javax.inject.Singleton

/**
 * Backend Component which exposes objects to any dependant module. Used like a Provider
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface BackendComponent {

    fun getRemoteDataStore() : RemoteListingDataStore

}
