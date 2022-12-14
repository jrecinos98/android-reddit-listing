package com.challenge.tti.ui.main.listings

import com.challenge.domain.entities.ListingType
import com.challenge.tti.CoroutineTestRule
import com.challenge.tti.mock.MockListingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListingsViewModelTest{
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    @Before
    fun setUp(){
    }
}