package com.reza.appnikestore

import android.app.Application
import com.reza.appnikestore.data.repo.ProductRepository
import com.reza.appnikestore.data.repo.ProductRepositoryImpl
import com.reza.appnikestore.data.repo.source.ProductLocalDataSource
import com.reza.appnikestore.data.repo.source.ProductRemoteDataSource
import com.reza.appnikestore.feature.main.MainViewModel
import com.reza.appnikestore.services.http.ApiService
import com.reza.appnikestore.services.http.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant()

        val myModules = module {
            single { createApiServiceInstance() }
            factory<ProductRepository> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }
            viewModel { MainViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}