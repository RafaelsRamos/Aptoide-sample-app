package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName

data class NetworkListAppsData(

    @SerializedName("hidden")
    val hidden: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("list")
    val appList: Array<NetworkApp>,

    @SerializedName("next")
    val next: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("total")
    val total: Int

)