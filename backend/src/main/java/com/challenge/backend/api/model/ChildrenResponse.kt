package com.challenge.backend.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ChildrenResponse (
    val kind : String?,
    val data : ChildrenData?
)