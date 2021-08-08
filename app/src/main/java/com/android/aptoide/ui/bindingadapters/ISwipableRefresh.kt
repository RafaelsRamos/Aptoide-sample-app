package com.android.aptoide.ui.bindingadapters

/**
 * Interface that can be implemented by fragments, when they have content that will be refreshed
 * by swiping on the [SwipeRefreshLayout]
 */
interface ISwipableRefresh {

    /**
     * Triggered when the user swipes the [SwipeRefreshLayout]
     */
    fun onSwiped()

}