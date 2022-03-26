package com.reza.appnikestore.feature.list

import androidx.lifecycle.MutableLiveData
import com.reza.appnikestore.common.NikeSingleObserver
import com.reza.appnikestore.common.NikeViewModel
import com.reza.appnikestore.common.asyncNetWorkRequest
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.data.repo.ProductRepository

class ProductListViewModel(val sort: Int, val productRepository: ProductRepository) :
    NikeViewModel() {

    val productsLiveData = MutableLiveData<List<Product>>()

    init {
        getProduct()
    }

    private fun getProduct() {
        progressBarLiveData.value = true
        productRepository.getProducts(sort)
            .asyncNetWorkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productsLiveData.value = t
                }

            })
    }
}