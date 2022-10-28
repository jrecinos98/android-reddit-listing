package com.challenge.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.storage.dao.ListingDao
import com.challenge.storage.dao.RemoteKeyDao
import com.challenge.storage.entities.DBListing
import com.challenge.storage.entities.DBRemoteKeys
import timber.log.Timber

@Database(
    entities = [
        DBListing::class,
        DBRemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun keysDao() :  RemoteKeyDao
    abstract fun listingsDao() : ListingDao

    companion object {
        const val DATABASE_NAME = "tti-database"
    }
}