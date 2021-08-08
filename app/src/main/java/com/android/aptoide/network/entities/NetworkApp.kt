package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkApp(

    @SerializedName("added")
    val addedDate: Date,

    @SerializedName("apk_tags")
    val apkTags: Array<String>,

    @SerializedName("downloads")
    val downloads: Int,

    @SerializedName("graphic")
    val graphic: String?,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("md5sum")
    val md5sum: String,

    @SerializedName("modified")
    val modified: Date,

    @SerializedName("name")
    val name: String,

    @SerializedName("package")
    val appPackage: String,

    @SerializedName("pdownloads")
    val pDownloads: Int,

    @SerializedName("rating")
    val rating: Float,

    @SerializedName("size")
    val size: Long,

    @SerializedName("store_id")
    val storeId: Int,

    @SerializedName("store_name")
    val storeName: String,

    @SerializedName("updated")
    val updated: Date,

    @SerializedName("uptype")
    val upType: String,

    @SerializedName("vercode")
    val versionCode: Int,

    @SerializedName("vername")
    val versionName: String,

    )