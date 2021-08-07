package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName

data class NetworkResponse(

    @SerializedName("listApps")
    val listApps: NetworkListApps

)
