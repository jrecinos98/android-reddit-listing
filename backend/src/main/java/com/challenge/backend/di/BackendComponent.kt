package com.challenge.backend.di

import com.challenge.domain.stores.listings.RemoteListingDataStore
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface BackendComponent {

    fun getRemoteDataStore() : RemoteListingDataStore

}
