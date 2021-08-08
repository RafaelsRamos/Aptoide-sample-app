package com.android.aptoide.network.repos

import android.util.Log
import com.android.aptoide.cache.databases.AppDao
import com.android.aptoide.models.App
import com.android.aptoide.network.AppsRetrofit
import com.android.mappers.cache.AppCacheMapper
import com.android.mappers.network.AppNetworkMapper
import com.android.utils.DataState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

private const val OK_STATUS = "OK"

class AppsListRepository
constructor(
    /** To store the data on cache */
    private val appDao: AppDao,

    /** [AppsRetrofit] instance to make the calls to */
    private val appsRetrofit: AppsRetrofit,

    /** [AppCacheMapper] to convert from, and to, Domain model */
    private val appsCacheMapper: AppCacheMapper,

    /** [AppNetworkMapper] to convert from, and to, Domain model */
    private val appsNetworkMapper: AppNetworkMapper
) {

    companion object {

        const val TAG = "AppsListRepository"

    }

    fun getEntireAppsList(): Observable<DataState<List<App>>> {

        return Observable.create { emitter ->

            appsRetrofit.getAppsList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        if (result.status == OK_STATUS) {
                            val appList = result.responses.listApps.dataSets.all.data.appList
                            emitter.onNext(DataState.Success(appsNetworkMapper.mapFromEntityList(appList)))
                            emitter.onComplete()
                        }
                    }, { throwable ->
                        emitter.onError(throwable)
                    })
        }
    }

    fun getAppsListRaw() {
        appsRetrofit.getAppsListRaw()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .retry(2)
            .doOnError { Log.e(TAG, it.stackTraceToString()) }
            .subscribe {

                println("|||| My response raw ${it.string()}")

            }
    }

}