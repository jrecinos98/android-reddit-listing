package com.challenge.backend.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingData(
    @Json(name = "after")
    val after: String?,

    @Json(name = "before")
    val before : String?,

    @Json(name = "dist")
    val dist : Int?,

    @Json(name = "modhash")
    val modHash : String?,

    @Json(name = "children")
    val children : List<ChildrenResponse>

)
