package com.android.aptoide.ui.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.aptoide.models.App
import com.android.aptoide.ui.itemdecorators.SpacesItemDecoration
import com.android.aptoide.ui.recycleradapters.EditorChoiceRecyclerAdapter
import com.android.aptoide.ui.recycleradapters.LocalTopAppsRecyclerAdapter
import com.android.aptoide.ui.viewmodels.AppDisplayFragmentViewModel
import com.android.utils.DataState

@BindingAdapter(value = ["editorSetAppsViewModel", "editorSetAppsLifecycleOwner"], requireAll = true)
fun fillEditorChoiceApps(rv: RecyclerView, viewModel: AppDisplayFragmentViewModel, lifecycleOwner: LifecycleOwner) {

    rv.run {
        adapter = EditorChoiceRecyclerAdapter(context, mutableListOf())
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(SpacesItemDecoration(top = 0, bottom = 0, start = 30, end = 30))
    }

    observeChange(viewModel.dataState, lifecycleOwner) { data ->
        (rv.adapter as EditorChoiceRecyclerAdapter).updateItems(data)
    }
}

@BindingAdapter(value = ["localTopSetAppsViewModel", "localTopetAppsLifecycleOwner"], requireAll = true)
fun fillLocalTopChoiceApps(rv: RecyclerView, viewModel: AppDisplayFragmentViewModel, lifecycleOwner: LifecycleOwner) {

    rv.run {
        adapter = LocalTopAppsRecyclerAdapter(context, mutableListOf())
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(SpacesItemDecoration(top = 0, bottom = 0, start = 20, end = 20))
    }

    observeChange(viewModel.dataState, lifecycleOwner) { data ->
        (rv.adapter as LocalTopAppsRecyclerAdapter).updateItems(data)
    }
}


fun observeChange(
    dataState: LiveData<DataState<List<App>>>,
    lifecycleOwner: LifecycleOwner,
    onSuccess: (List<App>) -> Unit
) {
    dataState.observe(lifecycleOwner, { observedData ->
        when (observedData) {
            is DataState.Success<List<App>> -> onSuccess.invoke(observedData.data)
        }
    })
}