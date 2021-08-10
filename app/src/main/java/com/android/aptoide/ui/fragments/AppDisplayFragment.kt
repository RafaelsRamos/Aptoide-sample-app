package com.android.aptoide.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.aptoide.R
import com.android.aptoide.databinding.FragmentAppDisplayBinding
import com.android.aptoide.models.App
import com.android.aptoide.ui.activities.MainActivity
import com.android.aptoide.ui.bindingadapters.ISwipableRefresh
import com.android.aptoide.ui.viewmodels.AppDisplayFragmentViewModel
import com.android.utils.DataState
import com.android.utils.createSimpleDialog
import com.android.utils.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class AppDisplayFragment: Fragment(), ISwipableRefresh {

    private lateinit var binding: FragmentAppDisplayBinding
    private val viewModel: AppDisplayFragmentViewModel by viewModels()

    // This cast is only for simplicity purposes.
    // With more time, either pass an interface to a "GenericFragment" class, to communicate with
    // the activity, or at least, the MainActivity must be a GenericActivity class, superclass to all
    // project's activities
    private val mainActivity get() = activity as MainActivity

    private val hasInternetConnection get() = isNetworkAvailable(requireContext())

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
                    Toast.makeText(context, "Data updated...", Toast.LENGTH_SHORT).show()
                    mainActivity.changeProgressBarState(false)
                }
                is DataState.Error -> {
                    showDataLoadErrorAlert()
                    mainActivity.changeProgressBarState(false)
                }
                is DataState.Loading -> {
                    // For this sample do nothing
                    // Ideally, we would display a progress bar (only if we didn't get here by swiping)
                }
            }

            mainActivity.resetSwipe()
        })
    }

    override fun onResume() {
        super.onResume()
        tryUpdateData()
    }

    private fun tryUpdateData() {
        try {

            // At this point our context is always valid
            if (hasInternetConnection) {
                viewModel.getAppList()
            } else {
                mainActivity.resetSwipe()
            }

            // If there is no internet connection, hide root view
            binding.root.visibility = if (hasInternetConnection) VISIBLE else GONE

        } catch (e: Exception) {
            println(e.stackTrace)
        }
    }

    override fun onSwiped() {
        tryUpdateData()
    }

    private fun showDataLoadErrorAlert() {
        context?.let { validContext ->
            createSimpleDialog(
                    validContext,
                    "Unable to fetch data",
                    "We're sorry, but we were unable to fetch the data for you."
            )
        }
    }
}