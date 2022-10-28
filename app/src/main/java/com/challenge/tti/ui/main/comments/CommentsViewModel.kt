package com.challenge.tti.ui.main.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.entities.ListingType
import com.challenge.domain.entities.PostComments
import com.challenge.domain.repositories.listings.ListingsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {


    fun getPostComments(postId : String, listingType: ListingType) : LiveData<PostComments>{

        val commentObs = MutableLiveData<PostComments>()
        viewModelScope.launch {
            commentObs.postValue(
                repository.getPostComments(postId, listingType)
            )
        }
        return commentObs
    }
}