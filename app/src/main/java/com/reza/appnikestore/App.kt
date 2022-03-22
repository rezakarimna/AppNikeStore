package com.reza.appnikestore

import android.app.Application
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import com.reza.appnikestore.data.repo.*
import com.reza.appnikestore.data.repo.source.*
import com.reza.appnikestore.feature.ProductDetailViewModel
import com.reza.appnikestore.feature.main.MainViewModel
import com.reza.appnikestore.feature.main.ProductListAdapter
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
            factory { ProductListAdapter(get()) }
            factory<BannerRepository> { BannerRepositoryImpl(BannerRemoteDataSource(get())) }
            factory<CommentRepository> { CommentRepositoryImpl(CommentRemoteDataSource(get())) }
            viewModel { MainViewModel(get(),get()) }
            viewModel { (bundel: Bundle) -> ProductDetailViewModel(bundel , get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}