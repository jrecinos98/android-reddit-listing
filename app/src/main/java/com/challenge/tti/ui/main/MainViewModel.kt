package com.challenge.tti.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.entities.ListingType
import com.challenge.domain.repositories.listings.ListingsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {

    fun test() {
        viewModelScope.launch {
           repository.getListing(
               "all",
               ListingType.NEW
           )
        }
    }
}