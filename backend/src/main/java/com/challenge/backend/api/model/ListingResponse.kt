package com.challenge.backend.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingResponse(
    @Json(name = "kind")
    val kind: kotlin.String,

    @Json(name = "data")
    val data : ListingData
)
