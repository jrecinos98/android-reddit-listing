package com.challenge.domain.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.stores.listings.LocalListingDataStore
import timber.log.Timber


@OptIn(ExperimentalPagingApi::class)
class ListingsPagerFactory private constructor() {

    lateinit var remoteMediator : ListingsRemoteMediator
    lateinit var localDataStore: LocalListingDataStore

    companion object{
        fun create() = ListingsPagerFactory()
    }

    fun addMediator(remoteMediator: ListingsRemoteMediator) : ListingsPagerFactory{
        this.remoteMediator = remoteMediator
        return this
    }

    fun addLocalDataStore(localDataStore : LocalListingDataStore) : ListingsPagerFactory{
        this.localDataStore = localDataStore
        return this
    }

    fun buildListingPager(
        subReddit : String,
        listingType: ListingType
    ) : Pager<Int, RedditPost> {
        assert(::remoteMediator.isInitialized){
            Timber.e("Remote Mediator not provided")
        }
        assert(::localDataStore.isInitialized){
            Timber.e("Local listing data store not provided")
        }
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            remoteMediator = remoteMediator.apply {
                setListingType(listingType)
                setSubReddit(subReddit)
            },
            pagingSourceFactory = {
                //TODO allow taking in subreddit + type param to load only relevant data from db
                localDataStore.getListings(subReddit)
            }
        )
    }
}