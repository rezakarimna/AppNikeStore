package com.reza.appnikestore.data.repo.source

import com.reza.appnikestore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDataSource {
    fun getProducts(sort:Int): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun addToFavorites() : Completable

    fun deleteFromFavorites() : Completable
}