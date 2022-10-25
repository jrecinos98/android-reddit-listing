package com.challenge.tti.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.repositories.listings.ListingsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: ListingsRepository,
    private val test : String
) : ViewModel() {
    // TODO: Implement the ViewModel

    fun test() {
        viewModelScope.launch {
           // repository.printTest()
        }
    }
}