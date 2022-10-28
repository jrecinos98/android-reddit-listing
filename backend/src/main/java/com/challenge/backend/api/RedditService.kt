package com.challenge.backend.api

import com.challenge.backend.api.model.ListingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("r/{subReddit}/{resName}")
    suspend fun getListing(
        @Path("subReddit") subReddit : kotlin.String,
        @Path("resName") resourceName : kotlin.String,
        @Query("after") nextKey : String? = null,
        @Query("limit") limit : Int? = 15,
    ) : ListingResponse

}