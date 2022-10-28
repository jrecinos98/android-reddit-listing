package com.challenge.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.storage.Constants
import com.challenge.storage.entities.DBRemoteKeys

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(DBRemoteKeys: DBRemoteKeys)

    @Query("SELECT * FROM ${DBRemoteKeys.TABLE} WHERE id = :subReddit")
    suspend fun getRemoteKeys(subReddit : String): DBRemoteKeys?

    @Query("DELETE FROM ${DBRemoteKeys.TABLE} WHERE id =:subReddit")
    suspend fun clearRemoteKeys(subReddit : String)
}