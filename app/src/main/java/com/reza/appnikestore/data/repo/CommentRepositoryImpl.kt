package com.reza.appnikestore.data.repo

import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.data.repo.source.CommentDataSource
import io.reactivex.Single

class CommentRepositoryImpl(private val commentDataSource: CommentDataSource): CommentRepository {
    override fun getAllComments(productId: Int): Single<List<Comment>> = commentDataSource.getAllComments(productId)

    override fun insertComment(): Single<Comment> {
        TODO("Not yet implemented")
    }
}