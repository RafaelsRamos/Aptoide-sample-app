package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName

data class NetworkListApps(

    @SerializedName("datasets")
    val dataSets: NetworkDataSets,

    @SerializedName("info")
    val info: NetworkInfo

)
