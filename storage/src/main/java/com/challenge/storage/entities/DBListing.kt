package com.challenge.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBListing.TABLE)
data class DBListing (
    val id : String,
    @ColumnInfo(name = SUB_REDDIT)
    val subReddit : String,
    @ColumnInfo(name= UPVOTES)
    val upvotes : Int,
    val downvotes : Int,
    val rewardsReceived : Int,
    val uid : String,
    val username : String,
    val title : String,
    val thumbnail : String,
    val url : String,
    val createdAt : Int,
    @PrimaryKey(autoGenerate = true)
    val key :Int = 0,
){
    companion object{
        const val TABLE = "listing"
        const val UPVOTES = "upvotes"
        const val SUB_REDDIT = "subReddit"
    }
}