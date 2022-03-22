package com.reza.appnikestore.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reza.appnikestore.common.EXTRA_KEY_DATA
import com.reza.appnikestore.common.NikeFragment
import com.reza.appnikestore.common.convertDpToPixel
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.databinding.FragmentMainBinding
import com.reza.appnikestore.feature.product.ProductDetailActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : NikeFragment() , ProductListAdapter.OnProductClickListener{
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding
    private val productListAdapter: ProductListAdapter by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.latestProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.latestProductsRv.adapter = productListAdapter
        productListAdapter.onProductClickListener = this

        mainViewModel.productsLiveData.observe(viewLifecycleOwner) {
            productListAdapter.products = it as ArrayList<Product>
        }
        mainViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }
        mainViewModel.bannerLiveData.observe(viewLifecycleOwner) {
            val bannerSliderAdapter = BannerSliderAdapter(this, it)
            binding.bannerSliderViewPager.adapter = bannerSliderAdapter
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

    override fun productClick(product: Product) {
        startActivity(Intent(requireContext() , ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , product)
        })
    }
}