package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName

data class NetworkInfo(

    @SerializedName("status")
    val status: String,

    @SerializedName("time")
    val time: NetworkTime

)