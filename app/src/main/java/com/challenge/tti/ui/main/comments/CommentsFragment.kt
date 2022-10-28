package com.challenge.tti.ui.main.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.domain.entities.ListingType
import com.challenge.domain.orEmpty
import com.challenge.tti.App
import com.challenge.tti.MainActivity
import com.challenge.tti.R
import com.challenge.tti.ViewModelFactory
import com.challenge.tti.databinding.FragmentCommentsBinding
import com.challenge.tti.databinding.FragmentMainBinding
import com.challenge.tti.ui.main.listings.ListingsViewModel
import timber.log.Timber
import javax.inject.Inject

class CommentsFragment : Fragment() {

    companion object{
        fun newInstance(
            postId : String,
            postTitle : String,
            listingType: ListingType
        ): CommentsFragment {
            val args = Bundle().apply {
                putString(POST_ID_KEY, postId)
                putString(POST_TITLE, postTitle)
                putSerializable(COMMENT_LISTING, listingType)
            }
            val fragment = CommentsFragment()
            fragment.arguments = args
            return fragment
        }

        const val POST_ID_KEY = "postId"
        const val POST_TITLE = "postTitle"
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
    private lateinit var commentsAdapter : CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsBinding.inflate(inflater, container, false)

        initViews()
        observeComments()
        setOnBackPressedCallback()

        return binding.root
    }

    private fun initViews(){
        setToolbarTitle(arguments?.getString(POST_TITLE))
        commentsAdapter = CommentsAdapter()
        binding.commentStore.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = commentsAdapter
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun setToolbarTitle(title : String?){
        if(title.isNullOrEmpty().not()){
            (requireActivity() as MainActivity).supportActionBar?.title = title
        }
    }

    private fun observeComments(){
        binding.progress.isVisible = true
        viewModel.getPostComments(
            arguments?.getString(POST_ID_KEY).orEmpty(),
            (arguments?.getSerializable(COMMENT_LISTING) ?: ListingType.TOP) as ListingType
        ).observe(viewLifecycleOwner){
            binding.progress.isVisible = false
            commentsAdapter.submitList(it.comments)
        }
    }

    private fun setOnBackPressedCallback(){
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    setToolbarTitle(resources.getString(R.string.app_name))
                    parentFragmentManager.popBackStack()
                }
            }
        )
    }

}