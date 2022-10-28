package com.challenge.tti.ui.main.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.challenge.domain.entities.ListingType
import com.challenge.domain.orEmpty
import com.challenge.tti.App
import com.challenge.tti.ViewModelFactory
import com.challenge.tti.databinding.FragmentCommentsBinding
import com.challenge.tti.databinding.FragmentMainBinding
import com.challenge.tti.ui.main.listings.ListingsViewModel
import timber.log.Timber
import javax.inject.Inject

class CommentsFragment : Fragment() {

    companion object{
        fun newInstance(postId : String, listingType: ListingType): CommentsFragment {
            val args = Bundle().apply {
                putString(POST_ID_KEY, postId)
                putSerializable(COMMENT_LISTING, listingType)
            }
            val fragment = CommentsFragment()
            fragment.arguments = args
            return fragment
        }

        const val POST_ID_KEY = "postId"
        const val COMMENT_LISTING = "listing"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CommentsViewModel>
    private val viewModel: CommentsViewModel by lazy {
        viewModelFactory.get<CommentsViewModel>(
            requireActivity()
        )
    }

    private lateinit var binding : FragmentCommentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsBinding.inflate(inflater, container, false)
        fetchComments()
        observeComments()
        return binding.root
    }

    private fun fetchComments(){
        viewModel.getPostComments(
            arguments?.getString(POST_ID_KEY).orEmpty(),
            (arguments?.getSerializable(COMMENT_LISTING) ?: ListingType.TOP) as ListingType
        )
    }

    fun observeComments(){
        viewModel.commentObs.observe(viewLifecycleOwner){
            Timber.d("Got comments: $it")
        }
    }

}