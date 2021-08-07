package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName

data class NetworkTime(

    @SerializedName("human")
    val human: String,

    @SerializedName("seconds")
    val seconds: Double

)
