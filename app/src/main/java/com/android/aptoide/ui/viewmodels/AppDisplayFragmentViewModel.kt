package com.android.aptoide.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.android.aptoide.models.App
import com.android.aptoide.network.repos.AppsListRepository
import com.android.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AppDisplayFragmentViewModel
    @Inject constructor(
        private val appsListRepository: AppsListRepository,
        private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    companion object {
        const val TAG = "AppDisplayViewModel"
    }

    private val _dataState: MutableLiveData<DataState<List<App>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<App>>> get() = _dataState

    fun getAppList() {
        //TODO ("Remove... Its here for testing purposes")
        appsListRepository.getAppsListRaw()

        appsListRepository.getEntireAppsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSubscribeTriggered())
    }

    private fun onSubscribeTriggered() = object : Observer<DataState<List<App>>> {

        override fun onSubscribe(d: Disposable?) {
            // Do nothing
        }

        override fun onError(e: Throwable?) {
            Log.e(TAG, "Error while loading data from API... ${e?.message}")
            _dataState.value = DataState.Error(Exception(e?.message))
            //TODO("Show error dialog")
        }

        override fun onComplete() {
            // Do nothing
        }

        override fun onNext(dataState: DataState<List<App>>?) {
            _dataState.value = dataState
        }

    }

}