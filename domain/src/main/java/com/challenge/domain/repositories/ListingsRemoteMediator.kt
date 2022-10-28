package com.challenge.domain.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.entities.RemoteKeys
import com.challenge.domain.stores.listings.LocalListingDataStore
import com.challenge.domain.stores.listings.RemoteListingDataStore
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ListingsRemoteMediator @Inject constructor(
    private val localDataStore : LocalListingDataStore,
    private val remoteDataStore: RemoteListingDataStore
) : RemoteMediator<Int, RedditPost>() {

    private var subReddit : String = ""
    private var listingType : ListingType = ListingType.NEW

    fun setSubReddit(subReddit : String){
        this.subReddit = subReddit
    }

    fun setListingType(listingType: ListingType){
        this.listingType = listingType
    }

    override suspend fun initialize(): InitializeAction {
        //SKIP_INITIAL_REFRESH prevents paging triggering remote refresh at start.
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RedditPost>
    ): MediatorResult {
        return try {
            Timber.d("RemoteMediator load() called for loadType: $loadType")
            val loadKey = when(loadType){
                LoadType.REFRESH -> {
                    //Trigger a database clean when user forces a refresh
                    localDataStore.clearListings(subReddit)
                    null
                }
                //Prepend is not currently supported
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    localDataStore.getPagingKeys(subReddit).nextKey ?: return MediatorResult.Success(true)
                }
            }

            //Retrieve a fresh batch of listings from remote using cached remoteKey
            val response = remoteDataStore.getSubredditListing(subReddit,listingType, loadKey)
            //Update local listing cache and new remote key
            localDataStore.updateListings(subReddit, response.posts, response.remoteKeys)

            //If nextKey is not null there are more listings available in remote source.
            MediatorResult.Success(
                endOfPaginationReached = response.remoteKeys.nextKey == null
            )

        } catch (e: IOException) {
            Timber.e("Error: $e")
            MediatorResult.Error(e)
        } catch (t : Throwable) {
            Timber.e("Error: $t")
            MediatorResult.Error(t)
        }

    }
}