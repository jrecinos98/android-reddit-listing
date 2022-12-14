package com.challenge.backend.api

import com.challenge.backend.Constants
import com.challenge.backend.api.model.ListingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    /**
     * Retrieves listing for the provided subReddit sorted by the listing type (new, random, top, etc..)
     */
    @GET("r/{subReddit}/{resName}.json")
    suspend fun getListing(
        @Path("subReddit") subReddit : String,
        @Path("resName") resourceName : String = Constants.Listings.TOP,
        @Query("after") nextKey : String? = null,
        @Query("limit") limit : Int? = 15,
    ) : ListingResponse

    /**
     * Retrieves comments for a Reddit Post matching the given id
     */
    @GET("comments/{postId}.json")
    suspend fun getPostComments(
        @Path("postId") postId : String,
        @Query("depth") depth : Int? = 1,
        @Query("limit") limit : Int? = 15,
        @Query("showMedia") media : Boolean? = false,
    ) : List<ListingResponse>

}