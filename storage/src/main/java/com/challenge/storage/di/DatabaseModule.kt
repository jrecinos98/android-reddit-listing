package com.challenge.storage.di

import android.content.Context
import androidx.room.Room
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.storage.AppDatabase
import com.challenge.storage.stores.LocalListingDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

/**
 * Dagger Module internal to the storage module. Any instance that is to be exposed will
 * be exposed by the Storage Component
 */
//Required annotation to prevent build time error with Hilt. Migration to Hilt Pending
@DisableInstallInCheck
@Module
class DatabaseModule {

    @Provides
    fun providesDataBase(appContext : Context) : AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
        .build()
    }

    @Singleton
    @Provides
    fun providesLocalListingDataStore(
        localStore : LocalListingDataStoreImpl
    ) : LocalListingDataStore {
        return localStore
    }
}