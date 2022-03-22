package com.reza.appnikestore.feature.product.comment

import androidx.lifecycle.MutableLiveData
import com.reza.appnikestore.common.NikeSingleObserver
import com.reza.appnikestore.common.NikeViewModel
import com.reza.appnikestore.common.asyncNetWorkRequest
import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.data.repo.CommentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentListViewModel(protectedId: Int, commentRepository: CommentRepository) :
    NikeViewModel() {
    val commentsLiveData = MutableLiveData<List<Comment>>()

    init {
        getComments(protectedId, commentRepository)
    }

    private fun getComments(protectedId: Int, commentRepository: CommentRepository) {
        commentRepository.getAllComments(protectedId)
            .asyncNetWorkRequest()
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentsLiveData.value = t
                }
            })
    }
}