package com.reza.appnikestore.data.repo.source

import com.reza.appnikestore.data.Comment
import io.reactivex.Single

interface CommentDataSource {
    fun getAllComments(productId: Int): Single<List<Comment>>

    fun insertComment(): Single<Comment>
}