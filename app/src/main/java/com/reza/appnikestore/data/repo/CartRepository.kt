package com.reza.appnikestore.data.repo

import com.reza.appnikestore.data.*
import io.reactivex.Single

interface CartRepository {

    fun addToCart(productId: Int): Single<AddToCartResponse>
    fun getCart(): Single<CartResponse>
    fun removeCart(cartItemId: Int): Single<MessageResponse>
    fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse>
    fun getCartItemsCount(): Single<CartItemCount>
}