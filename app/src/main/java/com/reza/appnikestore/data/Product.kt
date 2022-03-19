package com.reza.appnikestore.data

data class Product(
    val discount: Int,
    val id: Int,
    val image: String,
    val previous_price: Int,
    val price: Int,
    val status: Int,
    val title: String
)

const val SORT_LATEST = 0
const val SORT_POPULAR = 1
const val SORT_PRICE_DESC = 2
const val SORT_PRICE_ASC = 3