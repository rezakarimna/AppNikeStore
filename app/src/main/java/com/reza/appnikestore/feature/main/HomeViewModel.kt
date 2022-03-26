package com.reza.appnikestore.feature.main

import androidx.lifecycle.MutableLiveData
import com.reza.appnikestore.common.NikeSingleObserver
import com.reza.appnikestore.common.NikeViewModel
import com.reza.appnikestore.data.Banner
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.data.SORT_LATEST
import com.reza.appnikestore.data.repo.BannerRepository
import com.reza.appnikestore.data.repo.ProductRepository
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers

class HomeViewModel(productRepository: ProductRepository, bannerRepository: BannerRepository) :
    NikeViewModel() {
    val productsLiveData = MutableLiveData<List<Product>>()
    val bannerLiveData = MutableLiveData<List<Banner>>()

    init {
        progressBarLiveData.value = true
        productRepository.getProducts(SORT_LATEST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productsLiveData.value = t
                }
            })

        bannerRepository.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeSingleObserver<List<Banner>>(compositeDisposable) {
                override fun onSuccess(t: List<Banner>) {
                    bannerLiveData.value = t
                }
            })

    }
}