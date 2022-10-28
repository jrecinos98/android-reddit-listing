package com.challenge.tti.ui.main.comments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.RedditComments
import com.challenge.domain.repositories.listings.ListingsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {

    private val _commentsObs = MutableLiveData<RedditComments>()

    val commentObs = _commentsObs

    fun getPostComments(postId : String, listingType: ListingType){
        viewModelScope.launch {
            _commentsObs.postValue(
                repository.getPostComments(postId, listingType)
            )
        }
    }
}