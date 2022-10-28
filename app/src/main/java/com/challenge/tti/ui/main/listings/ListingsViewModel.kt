package com.challenge.tti.ui.main.listings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.challenge.domain.entities.ListingType
import com.challenge.domain.repositories.listings.ListingsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import javax.inject.Inject

class ListingsViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {

    val exceptionHandler : CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Timber.e(throwable)
    }

    suspend fun fetchRedditListings(
        subReddit : String = "pic",
        listingType : ListingType = ListingType.NEW
    ) = repository
        .getRedditListing(subReddit, listingType)
        .flow
        // cachedIn allows paging to remain active in the viewModel scope, so even if the UI
        // showing the paged data goes through lifecycle changes, pagination remains cached and
        // the UI does not have to start paging from the beginning when it resumes.
        .cachedIn(viewModelScope)
}