package com.android.aptoide.models

import java.util.*

/**
 * Data class that holds all the information regarding an App
 */
data class App(

    /** Add business description for the respective variable */
    val addedDate: Date,

    /** Add business description for the respective variable */
    val apkTags: Array<String>,

    /** Add business description for the respective variable */
    val downloads: Int,

    /** Add business description for the respective variable */
    val graphic: String,

    /** Add business description for the respective variable */
    val icon: String,

    /** Add business description for the respective variable */
    val id: Int,

    /** Add business description for the respective variable */
    val md5sum: String,

    /** Add business description for the respective variable */
    val modified: Date,

    /** Add business description for the respective variable */
    val name: String,

    /** Add business description for the respective variable */
    val appPackage: String,

    /** Add business description for the respective variable */
    val pDownloads: Int,

    /** Add business description for the respective variable */
    val rating: Float,

    /** Add business description for the respective variable */
    val size: Long,

    /** Add business description for the respective variable */
    val storeId: Int,

    /** Add business description for the respective variable */
    val storeName: String,

    /** Add business description for the respective variable */
    val updated: Date,

    /** Add business description for the respective variable */
    val upType: String,

    /** Add business description for the respective variable */
    val versionCode: Int,

    /** Add business description for the respective variable */
    val versionName: String,
)