package com.android.aptoide.ui.recycleradapters

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * RecyclerViewAdapter superclass to be used on RecyclerView adapters that will be updated dynamically
 * @param T         Type of data that will be displayed
 * @param VH        Subclass of [RecyclerView.ViewHolder] that also implements [IBindableViewHolder]
 * @property data   List of objects currently in the recyclerView
 */
abstract class LiveRecyclerViewAdapter<T, VH>(@ApplicationContext context: Context, var data: MutableList<T>): RecyclerView.Adapter<VH>() where VH: RecyclerView.ViewHolder, VH: IBindableViewHolder<T> {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size

    fun updateItems(newItems: List<T>) {
        data.clear()
        addItems(newItems)
    }

    fun addItems(newItems: List<T>) {
        data.addAll(newItems)
        notifyDataSetChanged()
    }
}