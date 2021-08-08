package com.android.utils

import java.lang.Exception

/**
 * Class that aims at sorting the result from API calls, so that the views can do their jobs
 * without worrying about business login
 */
sealed class DataState<out R> {

    /**
     * Triggered on API request successful
     */
    data class Success<out T>(val data: T): DataState<T>()

    /**
     * Triggered on API request failed
     */
    data class Error(val exception: Exception): DataState<Nothing>()

    /**
     * Triggered on API request made, to show loading animation
     */
    object Loading: DataState<Nothing>()

}