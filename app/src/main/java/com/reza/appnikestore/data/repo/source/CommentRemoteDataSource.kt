package com.reza.appnikestore.data.repo.source

import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.services.http.ApiService
import io.reactivex.Single

class CommentRemoteDataSource(val apiService: ApiService) : CommentDataSource {
    override fun getAllComments(productId: Int): Single<List<Comment>> =
        apiService.getComments(productId)

    override fun insertComment(): Single<Comment> {
        TODO("Not yet implemented")
    }
}