package com.reza.appnikestore.data.repo.source

import com.reza.appnikestore.data.AddToCartResponse
import com.reza.appnikestore.data.CartItemCount
import com.reza.appnikestore.data.CartResponse
import com.reza.appnikestore.data.MessageResponse
import io.reactivex.Single

interface CartDataSource {
    fun addToCart(productId: Int): Single<AddToCartResponse>
    fun getCart(): Single<CartResponse>
    fun removeCart(cartItemId: Int): Single<MessageResponse>
    fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse>
    fun getCartItemsCount(): Single<CartItemCount>
}