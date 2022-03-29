package com.reza.appnikestore.common

import android.util.Log
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

abstract class NikeCompletableObserver(val compositeDisposable: CompositeDisposable) :
    CompletableObserver {
    private val TAGNike = "NikeCompletableObserver"
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        EventBus.getDefault().post(NikeExceptionMapper.map(e))
    }
}