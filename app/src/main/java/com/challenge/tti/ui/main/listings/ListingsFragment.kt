package com.challenge.tti.ui.main.listings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.tti.App
import com.challenge.tti.ViewModelFactory
import com.challenge.tti.databinding.FragmentMainBinding
import javax.inject.Inject

class ListingsFragment : Fragment() {

    companion object {
        fun newInstance() = ListingsFragment()
    }

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
        listingAdapter = ListingsAdapter().apply {
            onItemClick = {

            }
        }

        binding.storesView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = listingAdapter
        }
        observeRedditListing()

        return binding.root
    }

    fun observeRedditListing(){
        viewModel.listingObs.observe(viewLifecycleOwner){
            listingAdapter.submitList(it)
        }
    }
}