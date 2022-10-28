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
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
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
                    localDataStore.clearListings(subReddit)
                    null
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    localDataStore.getPagingKeys(
                        subReddit
                    ).nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                }
            }
            val response = remoteDataStore.getSubredditListing(subReddit,listingType, loadKey)
            //Update local listing cache and new remote key
            localDataStore.updateListings(
                subReddit,
                response.posts,
                response.remoteKeys
            )
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