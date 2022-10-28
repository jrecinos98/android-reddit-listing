package com.challenge.tti.ui.main.comments

import androidx.lifecycle.ViewModel
import com.challenge.domain.repositories.listings.ListingsRepository
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {


}