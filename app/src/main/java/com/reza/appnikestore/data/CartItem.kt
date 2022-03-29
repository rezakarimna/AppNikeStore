package com.reza.appnikestore.data

import com.reza.appnikestore.data.Product

data class CartItem(
    val cart_item_id: Int,
    var count: Int,
    val product: Product,
    var changeCountProgressBarIsVisible: Boolean = false
)