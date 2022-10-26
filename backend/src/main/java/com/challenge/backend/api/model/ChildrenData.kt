package com.challenge.backend.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChildrenData(
    @Json(name = "id")
    val id : String,

    @Json(name = "subreddit")
    val subReddit : String?,

    @Json(name = "ups")
    val upvotes : Int?,

    @Json(name = "downs")
    val downvotes : Int?,

    @Json(name = "total_awards_received")
    val rewardsReceived : Int?,

    @Json(name = "author_fullname")
    val uid : String?,

    @Json(name = "author")
    val username : String?,

    @Json(name = "title")
    val title : String?,

    @Json(name = "thumbnail")
    val thumbnail : String?,

    @Json(name = "url")
    val url : String?,

    @Json(name = "created_utc")
    val createdAtUtc : Int?
)
