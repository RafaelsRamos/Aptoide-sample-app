package com.android.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


const val DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss"

const val SERVICE_TIMEOUT_DURATION = 5000

/**
 * Set a gradient to the activity's [Window] background, allowing for a smooth
 * status bar + action bar transition
 * @param activity Reference to an [Activity]
 */
fun setStatusBarDrawable(activity: Activity, @DrawableRes drawableRes: Int) {
    with(activity.window) {
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
        setBackgroundDrawable(ContextCompat.getDrawable(activity, drawableRes))
    }
}

/**
 * Check if user has internet connection
 * @return True if the user has internet connection, False otherwise
 */
fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
        return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        val networks = cm.allNetworks
        for (n in networks) {
            val nInfo: NetworkInfo? = cm.getNetworkInfo(n)
            if (nInfo != null && nInfo.isConnected) return true
        }
    }
    return false
}

fun createSimpleDialog(ctx: Context, title: String, message: String, btnMessage: String = "Continue") {
    with (AlertDialog.Builder(ctx).create()) {
        setTitle(title)
        setMessage(message)
        setButton(AlertDialog.BUTTON_NEUTRAL, btnMessage) { dialog, _ -> dialog.dismiss() }
        show()
    }
}