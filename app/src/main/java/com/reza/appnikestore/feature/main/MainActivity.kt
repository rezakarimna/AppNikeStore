package com.reza.appnikestore.feature.main

import android.os.Bundle
import com.reza.appnikestore.R
import com.reza.appnikestore.common.NikeActivity

class MainActivity : NikeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}