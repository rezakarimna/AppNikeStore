package com.reza.appnikestore.data.repo.source

import com.reza.appnikestore.data.Banner
import com.reza.appnikestore.services.http.ApiService
import io.reactivex.Single

class BannerRemoteDataSource(private val apiService: ApiService) : BannerDataSource {
    override fun getBanners(): Single<List<Banner>> = apiService.getBanners()
}