package com.challenge.tti.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.challenge.tti.App
import com.challenge.tti.R
import com.challenge.tti.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private val viewModel: MainViewModel by lazy {
        viewModelFactory.get<MainViewModel>(
            requireActivity()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.d("")
        viewModel.test()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}