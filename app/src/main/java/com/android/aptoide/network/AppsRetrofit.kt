package com.android.aptoide.network

import com.android.aptoide.network.entities.NetworkResult
import io.reactivex.rxjava3.core.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * List of Retrofit requests that enquire Apps
 */
interface AppsRetrofit {

    @GET("listApps")
    fun getAppsList(): Flowable<NetworkResult>

}