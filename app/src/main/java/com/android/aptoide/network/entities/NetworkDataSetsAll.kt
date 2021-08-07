package com.android.aptoide.network.entities

import com.google.gson.annotations.SerializedName

data class NetworkDataSetsAll(

    @SerializedName("data")
    val data: NetworkListAppsData

)
