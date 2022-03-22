package com.reza.appnikestore.feature

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.reza.appnikestore.common.EXTRA_KEY_DATA
import com.reza.appnikestore.common.NikeSingleObserver
import com.reza.appnikestore.common.NikeViewModel
import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.data.repo.CommentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(bundle: Bundle,commentRepository: CommentRepository):NikeViewModel() {
    val productLiveData = MutableLiveData<Product>()
    val commentLiveData = MutableLiveData<List<Comment>>()
    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        commentRepository.getAllComments(productLiveData.value!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentLiveData.value = t
                }
            })
    }
}