package com.challenge.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.storage.Constants


@Entity(tableName = DBRemoteKeys.TABLE)
data class DBRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevKey: String?,
    val nextKey: String?
){
    companion object{
        const val TABLE = "remote_keys"
    }
}