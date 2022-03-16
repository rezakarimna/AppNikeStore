package com.reza.appnikestore.common

import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class NikeSingleObserver<T>(private val compositeDisposable: CompositeDisposable) : SingleObserver<T> {
    private val TAGNike = "NikeSingleObserver"

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        Log.i(TAGNike, "onError: $e")
    }
}