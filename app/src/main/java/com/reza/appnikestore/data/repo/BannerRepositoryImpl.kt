package com.reza.appnikestore.data.repo

import com.reza.appnikestore.data.Banner
import com.reza.appnikestore.data.repo.source.BannerDataSource
import io.reactivex.Single

class BannerRepositoryImpl(private val bannerRemoteDataSource: BannerDataSource) : BannerRepository {

    override fun getBanners(): Single<List<Banner>> = bannerRemoteDataSource.getBanners()
}