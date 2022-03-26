package com.reza.appnikestore

import android.app.Application
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import com.reza.appnikestore.data.repo.*
import com.reza.appnikestore.data.repo.source.*
import com.reza.appnikestore.feature.ProductDetailViewModel
import com.reza.appnikestore.feature.common.ProductListAdapter
import com.reza.appnikestore.feature.list.ProductListViewModel
import com.reza.appnikestore.feature.main.HomeViewModel
import com.reza.appnikestore.feature.product.comment.CommentListViewModel
import com.reza.appnikestore.services.FrescoImageLoadingService
import com.reza.appnikestore.services.ImageLoadingService
import com.reza.appnikestore.services.http.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        val myModules = module {
            single { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }

            factory<ProductRepository> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }
            factory { (viewType: Int) -> ProductListAdapter(viewType, get()) }
            factory<BannerRepository> { BannerRepositoryImpl(BannerRemoteDataSource(get())) }
            factory<CommentRepository> { CommentRepositoryImpl(CommentRemoteDataSource(get())) }
            viewModel { HomeViewModel(get(), get()) }
            viewModel { (bundel: Bundle) -> ProductDetailViewModel(bundel, get()) }
            viewModel { (productId: Int) -> CommentListViewModel(productId, get()) }
            viewModel { (sort: Int) -> ProductListViewModel(sort, get()) }

        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}