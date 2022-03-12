package com.reza.appnikestore.data.repo

import com.reza.appnikestore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {

    fun getProducts(): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun addToFavorites() : Completable

    fun deleteFromFavorites() : Completable
}