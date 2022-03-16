package com.reza.appnikestore.feature.main

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reza.appnikestore.common.NikeFragment
import com.reza.appnikestore.common.convertDpToPixel
import com.reza.appnikestore.databinding.FragmentMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : NikeFragment() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.productsLiveData.observe(viewLifecycleOwner) {
            // Log.i("MainFragment", "onViewCreated: $it ")
        }
        mainViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }
        mainViewModel.bannerLiveData.observe(viewLifecycleOwner) {
            val bannerSliderAdapter = BannerSliderAdapter(this, it)
            binding.bannerSliderViewPager.adapter = bannerSliderAdapter
            /////////////////////////////////
            binding.bannerSliderViewPager.adapter = bannerSliderAdapter
            val viewPagerHeight = (((binding.bannerSliderViewPager.measuredWidth - convertDpToPixel(
                32f,
                requireContext()
            )) * 173) / 328).toInt()

            val layoutParams = binding.bannerSliderViewPager.layoutParams
            layoutParams.height = viewPagerHeight
            binding.bannerSliderViewPager.layoutParams = layoutParams

            binding.sliderIndicator.setViewPager2(binding.bannerSliderViewPager)
        }
    }
}