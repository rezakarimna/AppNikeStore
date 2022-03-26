package com.reza.appnikestore.feature.home

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
import com.reza.appnikestore.data.SORT_LATEST
import com.reza.appnikestore.databinding.FragmentHomeBinding
import com.reza.appnikestore.feature.common.ProductListAdapter
import com.reza.appnikestore.feature.common.VIEW_TYPE_ROUND
import com.reza.appnikestore.feature.list.ProductListActivity
import com.reza.appnikestore.feature.main.BannerSliderAdapter
import com.reza.appnikestore.feature.main.HomeViewModel
import com.reza.appnikestore.feature.product.ProductDetailActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : NikeFragment(), ProductListAdapter.ProductEventListener {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private val productListAdapter: ProductListAdapter by inject { parametersOf(VIEW_TYPE_ROUND) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.latestProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.latestProductsRv.adapter = productListAdapter
        productListAdapter.productEventListener = this

        homeViewModel.productsLiveData.observe(viewLifecycleOwner) {
            productListAdapter.products = it as ArrayList<Product>
        }
        homeViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }
        homeViewModel.bannerLiveData.observe(viewLifecycleOwner) {
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
        moveToProductListActivity()
    }

    override fun onProductClick(product: Product) {
        startActivity(Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, product)
        })
    }

    fun moveToProductListActivity() {
        binding.viewLatestProductsBtn.setOnClickListener {
            startActivity(Intent(requireContext(), ProductListActivity::class.java).apply {
                putExtra(EXTRA_KEY_DATA, SORT_LATEST)
            })
        }
    }
}