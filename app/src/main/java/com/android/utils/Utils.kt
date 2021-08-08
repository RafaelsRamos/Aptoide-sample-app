package com.android.utils

import android.app.Activity
import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import java.net.InetAddress

const val DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss"

/**
 * Set a gradient to the activity's [Window] background, allowing for a smooth
 * status bar + action bar transition
 * @param activity Reference to an [Activity]
 */
fun setStatusBarDrawable(activity: Activity, @DrawableRes drawableRes: Int ) {
    with (activity.window) {
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
        setBackgroundDrawable(ContextCompat.getDrawable(activity, drawableRes))
    }
}

/**
 * Check if user has internet connection, by trying to access www.google.com
 * @return True if the user has internet connection, False otherwise
 */
fun hasInternetConnection(): Boolean {
    return try {
        InetAddress.getByName("google.com").equals("")
    } catch (e: Exception) {
        false
    }
}