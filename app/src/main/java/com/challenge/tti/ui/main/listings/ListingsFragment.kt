package com.challenge.tti.ui.main.listings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.domain.entities.ListingType
import com.challenge.tti.App
import com.challenge.tti.R
import com.challenge.tti.ViewModelFactory
import com.challenge.tti.databinding.FragmentMainBinding
import com.challenge.tti.ui.main.comments.CommentsFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ListingsFragment : Fragment() {

    companion object {
        fun newInstance() = ListingsFragment()

        val SUB_REDDIT = "pic"
        val LISTING_TYPE = ListingType.NEW
    }

    private var listingCoroutine : Job? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListingsViewModel>
    private val viewModel: ListingsViewModel by lazy {
        viewModelFactory.get<ListingsViewModel>(
            requireActivity()
        )
    }

    private lateinit var binding : FragmentMainBinding

    private lateinit var listingAdapter : ListingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initViews()
        observeListingsFlow()
        observeLoadState()

        return binding.root
    }

    private fun initViews(){
        listingAdapter = ListingsAdapter().apply {
            onItemClick = {
                navigateToCommentFragment(it.id, it.title)
            }
        }

        binding.storesView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = listingAdapter
        }

        binding.swipeContainer.setOnRefreshListener {
            listingAdapter.refresh()
        }
    }

    private fun observeListingsFlow(){
        viewLifecycleOwner.lifecycleScope.launch {
            listingCoroutine?.cancelAndJoin()
            listingCoroutine = viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.fetchRedditListings(
                        SUB_REDDIT,
                        LISTING_TYPE
                    ).collectLatest {
                        listingAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun observeLoadState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                listingAdapter.loadStateFlow.collect {
                    binding.swipeContainer.isRefreshing = it.mediator?.refresh is LoadState.Loading
                    binding.appendProgress.isVisible = it.mediator?.append is LoadState.Loading
                }
            }
        }
    }

    private fun navigateToCommentFragment(postId : String, title : String){
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                CommentsFragment.newInstance(
                    postId,
                    title,
                    LISTING_TYPE
                )
            )
            .addToBackStack(null)
            .commit()
    }
}