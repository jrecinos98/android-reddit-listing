package com.challenge.storage.di

import android.app.Application
import android.content.Context
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.storage.AppDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
* Storage Component which exposes objects to any dependant module
*/
@Singleton
@Component(modules = [DatabaseModule::class])
interface StorageComponent {

    @Component.Factory
    interface Factory{
        //@BindInstance will allow this component to inject this object to any
        //of its modules or classes that require injection.
        fun create( @BindsInstance appContext  : Context) : StorageComponent
    }

    @Singleton
    fun getLocalDataStore() : LocalListingDataStore

}