package com.reza.appnikestore.data.repo.source

import com.reza.appnikestore.data.Banner
import io.reactivex.Single

interface BannerDataSource {
    fun getBanners(): Single<List<Banner>>
}