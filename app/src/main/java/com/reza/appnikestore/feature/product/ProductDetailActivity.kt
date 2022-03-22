package com.reza.appnikestore.feature.product

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reza.appnikestore.R
import com.reza.appnikestore.common.formatPrice
import com.reza.appnikestore.common.implementSpringAnimationTrait
import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.databinding.ActivityProductDetailBinding
import com.reza.appnikestore.feature.ProductDetailViewModel
import com.reza.appnikestore.services.ImageLoadingService
import com.reza.appnikestore.view.scroll.ObservableScrollViewCallbacks
import com.reza.appnikestore.view.scroll.ScrollState
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    private val productDetailViewModel: ProductDetailViewModel by viewModel { parametersOf(intent.extras) }
    private val imageLoadingService: ImageLoadingService by inject()
    private val commentAdapter = CommentAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeProduct()
        binding.productIv.post {
            setAnimationScrollImageProduct()
        }

        observeListComments()
        intiRecyclerView()
    }

    private fun observeProduct() {
        productDetailViewModel.productLiveData.observe(this) {
            imageLoadingService.load(binding.productIv, it.image)
            binding.apply {
                titleTV.text = it.title
                currentPriceTv.text = formatPrice(it.price)
                previousProductTV.text = formatPrice(it.previous_price)
                previousProductTV.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                toolbarTitleTv.text = it.title
            }
        }
    }

    private fun setAnimationScrollImageProduct() {
        val productIvHeight = binding.productIv.height
        binding.ObservableScrollView.addScrollViewCallbacks(object : ObservableScrollViewCallbacks {
            override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
                binding.toolbarView.alpha = scrollY.toFloat() / productIvHeight.toFloat()
                binding.productIv.translationY = scrollY.toFloat() / 2
            }

            override fun onDownMotionEvent() {

            }

            override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

            }

        })
    }

    private fun intiRecyclerView() {
        binding.commentsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.commentsRv.adapter = commentAdapter
    }

    private fun observeListComments() {
        productDetailViewModel.commentLiveData.observe(this) {
            commentAdapter.comments = it as ArrayList<Comment>
            if (it.size > 3)
                binding.viewAllCommentsBtn.visibility = View.VISIBLE
        }
    }

}