package com.reza.appnikestore.data.repo

import com.reza.appnikestore.data.Comment
import io.reactivex.Single

interface CommentRepository {
    fun getAllComments(productId: Int): Single<List<Comment>>

    fun insertComment(): Single<Comment>
}