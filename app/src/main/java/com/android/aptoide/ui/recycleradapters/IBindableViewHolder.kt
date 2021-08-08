package com.android.aptoide.ui.recycleradapters

/**
 * Interface that will expose a [bind] method from a ViewHolders, so that they can be
 * populated generically by [LiveRecyclerViewAdapter]
 */
interface IBindableViewHolder<T> {

    fun bind(value: T)

}