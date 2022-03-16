package com.reza.appnikestore.services

import com.reza.appnikestore.view.NikeImageView

interface ImageLoadingService {
    fun load(imageView: NikeImageView , imageUrl:String)
}