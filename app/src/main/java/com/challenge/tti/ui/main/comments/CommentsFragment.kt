package com.challenge.tti.ui.main.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.challenge.tti.App
import com.challenge.tti.ViewModelFactory
import com.challenge.tti.databinding.FragmentCommentsBinding
import com.challenge.tti.databinding.FragmentMainBinding
import com.challenge.tti.ui.main.listings.ListingsViewModel
import javax.inject.Inject

class CommentsFragment : Fragment() {

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

        return binding.root
    }

}