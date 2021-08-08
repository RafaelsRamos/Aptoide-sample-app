package com.android.aptoide.ui.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.aptoide.models.App
import com.android.aptoide.ui.itemdecorators.SpacesItemDecoration
import com.android.aptoide.ui.recycleradapters.EditorChoiceRecyclerAdapter
import com.android.aptoide.ui.viewmodels.AppDisplayFragmentViewModel
import com.android.utils.DataState

@BindingAdapter(value = ["setAppsViewModel", "setAppsLifecycleOwner"], requireAll = true)
fun fillEditorChoiceApps(rv: RecyclerView, viewModel: AppDisplayFragmentViewModel, lifecycleOwner: LifecycleOwner) {

    rv.run {
        adapter = EditorChoiceRecyclerAdapter(context, mutableListOf())
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(SpacesItemDecoration(18))
    }

    viewModel.dataState.observe(lifecycleOwner, { dataState ->
        when (dataState) {
            is DataState.Success<List<App>> -> {
                (rv.adapter as EditorChoiceRecyclerAdapter).updateItems(dataState.data)
            }
        }
    })

}