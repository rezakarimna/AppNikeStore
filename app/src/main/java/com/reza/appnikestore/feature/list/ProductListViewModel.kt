package com.reza.appnikestore.feature.list

import androidx.lifecycle.MutableLiveData
import com.reza.appnikestore.R
import com.reza.appnikestore.common.NikeSingleObserver
import com.reza.appnikestore.common.NikeViewModel
import com.reza.appnikestore.common.asyncNetWorkRequest
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.data.repo.ProductRepository

class ProductListViewModel(var sort: Int, private val productRepository: ProductRepository) :
    NikeViewModel() {

    val productsLiveData = MutableLiveData<List<Product>>()
    val selectedSortTitleLiveData = MutableLiveData<Int>()
    private val sortTitles = arrayOf(
        R.string.sortLatest,
        R.string.sortPopular,
        R.string.sortPriceHighToLow,
        R.string.sortPriceLowToHigh
    )

    init {
        getProduct()
        selectedSortTitleLiveData.value = sortTitles[sort]
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

    fun onSelectedSortChangedByUser(sort: Int) {
        this.sort = sort
        this.selectedSortTitleLiveData.value = sortTitles[sort]
        getProduct()
    }
}