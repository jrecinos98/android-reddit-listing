package com.challenge.tti.ui.main.listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditListing
import com.challenge.domain.repositories.listings.ListingsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ListingsViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {

    private val _listingsLiveData = MutableLiveData<List<RedditListing>>()

    val listingObs : LiveData<List<RedditListing>>
        get() = _listingsLiveData

    private val exceptionHandler : CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Timber.e(throwable)
    }

    init {
        fetchListings()
    }

    fun fetchListings (
        subReddit : String = "Android",
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
}