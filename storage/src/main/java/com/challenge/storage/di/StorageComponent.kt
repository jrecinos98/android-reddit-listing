package com.challenge.storage.di

import android.app.Application
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.storage.AppDatabase
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface StorageComponent {

    @Component.Factory
    interface Factory{
        fun create( @BindsInstance app  : Application) : StorageComponent
    }

    fun getLocalDataStore() : LocalListingDataStore

}