package com.challenge.tti.ui.main.listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditPost
import com.challenge.domain.repositories.listings.ListingsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ListingsViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {

    private val _listingsLiveData = MutableLiveData<List<RedditPost>>()

    val listingObs : LiveData<List<RedditPost>>
        get() = _listingsLiveData

    private var _listingsFlow : Flow<PagingData<RedditPost>> = flow {

    }

    val listingsFlow : Flow<PagingData<RedditPost>>
        get() = _listingsFlow

    private val exceptionHandler : CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Timber.e(throwable)
    }

    init {
        //fetchRedditListings()
    }

    fun fetchListings (
        subReddit : String = "pic",
        listingType : ListingType = ListingType.NEW
    ) {
        viewModelScope.launch(exceptionHandler) {
           _listingsLiveData.postValue(
               repository.getListing(
                   subReddit,
                   listingType
               )
           )
        }
    }

    suspend fun fetchRedditListings(
        subReddit : String = "pic",
        listingType : ListingType = ListingType.NEW
    ) = run {
        repository
            .getRedditListing(subReddit, listingType)
            .flow
            // cachedIn allows paging to remain active in the viewModel scope, so even if the UI
            // showing the paged data goes through lifecycle changes, pagination remains cached and
            // the UI does not have to start paging from the beginning when it resumes.
            .cachedIn(viewModelScope)

    }
}