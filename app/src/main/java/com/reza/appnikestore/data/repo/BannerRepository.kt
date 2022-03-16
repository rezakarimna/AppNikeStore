package com.reza.appnikestore.data.repo

import com.reza.appnikestore.data.Banner
import io.reactivex.Single

interface BannerRepository {
    fun getBanners(): Single<List<Banner>>
}