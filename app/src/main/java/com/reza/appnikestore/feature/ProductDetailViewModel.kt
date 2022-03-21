package com.reza.appnikestore.feature

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.reza.appnikestore.common.EXTRA_KEY_DATA
import com.reza.appnikestore.common.NikeViewModel
import com.reza.appnikestore.data.Product

class ProductDetailViewModel(bundle: Bundle):NikeViewModel() {
    val productLiveData = MutableLiveData<Product>()

    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
    }
}