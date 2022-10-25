package com.challenge.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBListing.TABLE)
data class DBListing (
    @PrimaryKey
    val name : String
){
    companion object{
        const val TABLE = "listing"
    }
}