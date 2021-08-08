package com.android.aptoide.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.aptoide.R
import com.android.aptoide.databinding.FragmentAppDisplayBinding
import com.android.aptoide.models.App
import com.android.aptoide.ui.activities.MainActivity
import com.android.aptoide.ui.viewmodels.AppDisplayFragmentViewModel
import com.android.utils.DataState
import com.android.utils.hasInternetConnection
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class AppDisplayFragment: Fragment() {

    private lateinit var binding: FragmentAppDisplayBinding
    private val viewModel: AppDisplayFragmentViewModel by viewModels()

    // This cast is only for simplicity purposes.
    // With more time, either pass an interface to a "GenericFragment" class, to communicate with
    // the activity, or at least, the MainActivity must be a GenericActivity class, superclass to all
    // project's activities
    private val mainActivity get() = activity as MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_display, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<App>> -> {
                    //TODO ("Hide progress bar")
                    mainActivity.changeProgressBarState(false)
                }
                is DataState.Error -> {
                    //TODO ("Hide progress bar")
                    mainActivity.changeProgressBarState(false)
                }
                is DataState.Loading -> {
                    mainActivity.changeProgressBarState(true)
                }
            }
        })

        if (hasInternetConnection()) {
            //TODO ("Understand why this is not working")
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            viewModel.getAppList()
        } catch (e: Exception) {
            println(e.stackTrace)
        }
    }
}