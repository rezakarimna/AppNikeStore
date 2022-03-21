package com.reza.appnikestore.feature.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reza.appnikestore.common.formatPrice
import com.reza.appnikestore.common.implementSpringAnimationTrait
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.databinding.ItemProductBinding
import com.reza.appnikestore.services.ImageLoadingService

class ProductListAdapter(val imageLoadingService: ImageLoadingService) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var onProductClickListener: OnProductClickListener? = null
    var products = ArrayList<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(product: Product) {
            imageLoadingService.load(binding.productIv, product.image)
            binding.apply {
                productTitleTV.text = product.title
                currentPriceTv.text = formatPrice(product.price)
                previousProductTV.text = formatPrice(product.previous_price)
                previousProductTV.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                root.implementSpringAnimationTrait()
                root.setOnClickListener { onProductClickListener?.productClick(product) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    override fun getItemCount(): Int = products.size

    interface OnProductClickListener {
        fun productClick(product: Product)
    }
}