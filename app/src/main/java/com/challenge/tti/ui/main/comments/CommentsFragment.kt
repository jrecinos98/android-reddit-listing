package com.challenge.tti.ui.main.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.domain.entities.ListingType
import com.challenge.domain.orEmpty
import com.challenge.tti.*
import com.challenge.tti.databinding.FragmentCommentsBinding
import com.challenge.tti.databinding.FragmentMainBinding
import com.challenge.tti.ui.main.listings.ListingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private val args : CommentsFragmentArgs by navArgs()

    private val viewModel: CommentsViewModel by viewModels()

    private lateinit var binding : FragmentCommentsBinding
    private lateinit var commentsAdapter : CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
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
        setToolbarTitle(args.title)
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
            (requireActivity() as MainActivity).supportActionBar?.title = title.toSpannedHtml()
        }
    }

    private fun observeComments(){
        binding.progress.isVisible = true
        viewModel.getPostComments(
            args.id,
            args.type
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