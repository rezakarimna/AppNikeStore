package com.reza.appnikestore.services

import com.facebook.drawee.view.SimpleDraweeView
import com.reza.appnikestore.view.NikeImageView

class FrescoImageLoadingService : ImageLoadingService {
    override fun load(imageView: NikeImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(imageUrl)
        else
            throw IllegalStateException("ImageView must be instance of SimpleDraweeView")
    }
}