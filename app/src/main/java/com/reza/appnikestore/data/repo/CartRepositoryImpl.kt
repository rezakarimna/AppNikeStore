package com.reza.appnikestore.data.repo

import com.reza.appnikestore.data.AddToCartResponse
import com.reza.appnikestore.data.CartItemCount
import com.reza.appnikestore.data.CartResponse
import com.reza.appnikestore.data.MessageResponse
import com.reza.appnikestore.data.repo.source.CartDataSource
import io.reactivex.Single

class CartRepositoryImpl(val remoteDataSource: CartDataSource) : CartRepository {
    override fun addToCart(productId: Int): Single<AddToCartResponse> =
        remoteDataSource.addToCart(productId)

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