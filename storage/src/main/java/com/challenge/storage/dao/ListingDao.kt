package com.challenge.storage.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.domain.entities.RedditPost
import com.challenge.storage.entities.DBListing
import com.challenge.storage.entities.DBRemoteKeys

@Dao
interface ListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateListings(listings : List<DBListing>)

    @Query(
        "SELECT * FROM ${DBListing.TABLE} WHERE ${DBListing.SUB_REDDIT} = :subReddit ORDER BY `key`"
    )
    fun getListings(subReddit : String) : PagingSource<Int, RedditPost>

    @Query("DELETE FROM ${DBListing.TABLE} WHERE ${DBListing.SUB_REDDIT} =:subReddit")
    fun clearListings(subReddit: String)
}