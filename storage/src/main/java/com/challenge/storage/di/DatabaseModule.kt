package com.challenge.storage.di

import android.app.Application
import androidx.room.Room
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.storage.AppDatabase
import com.challenge.storage.stores.LocalListingDataStoreImpl
import dagger.Module
import dagger.Provides

@Module
internal class DatabaseModule {
    @Provides
    fun providesDataBase(app : Application) : AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun providesLocalListingDataStore(
        localStore : LocalListingDataStoreImpl
    ) : LocalListingDataStore {
        return localStore
    }
}