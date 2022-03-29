package com.reza.appnikestore.data.repo.source

import com.google.gson.JsonObject
import com.reza.appnikestore.data.AddToCartResponse
import com.reza.appnikestore.data.CartItemCount
import com.reza.appnikestore.data.CartResponse
import com.reza.appnikestore.data.MessageResponse
import com.reza.appnikestore.services.http.ApiService
import io.reactivex.Single

class CartRemoteDataSource(val apiService: ApiService) : CartDataSource {
    override fun addToCart(productId: Int): Single<AddToCartResponse> = apiService.addToCart(
        JsonObject().apply { addProperty("product_id", productId) }
    )

    override fun getCart(): Single<CartResponse> {
        TODO("Not yet implemented")
    }

    override fun removeCart(cartItemId: Int): Single<MessageResponse> {
        TODO("Not yet implemented")
    }

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> {
        TODO("Not yet implemented")
    }

    override fun getCartItemsCount(): Single<CartItemCount> {
        TODO("Not yet implemented")
    }
}