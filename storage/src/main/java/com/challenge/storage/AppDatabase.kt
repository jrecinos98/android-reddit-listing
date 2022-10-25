package com.challenge.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.storage.entities.DBListing

@Database(
    entities = [
        DBListing::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "tti-database"
    }

}