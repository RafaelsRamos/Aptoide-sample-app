package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName

data class NetworkResult(

    @SerializedName("responses")
    val responses: NetworkResponse,

    @SerializedName("status")
    val status: String

)
