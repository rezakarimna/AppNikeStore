package com.reza.appnikestore.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reza.appnikestore.R
import com.reza.appnikestore.common.EXTRA_KEY_DATA
import com.reza.appnikestore.data.Banner
import com.reza.appnikestore.services.ImageLoadingService
import com.reza.appnikestore.view.NikeImageView
import org.koin.android.ext.android.inject

class BannerFragment : Fragment() {
    val imageLoadingService: ImageLoadingService by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageView =
            inflater.inflate(R.layout.fragment_banner, container, false) as NikeImageView
        val banner = requireArguments().getParcelable<Banner>(EXTRA_KEY_DATA)
            ?: throw IllegalStateException("Banner cannot be null")
        imageLoadingService.load(imageView, banner.image)
        return imageView
    }

    companion object {
        fun newInstance(banner: Banner): BannerFragment {
            return BannerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_KEY_DATA, banner)
                }
            }
        }
    }
}