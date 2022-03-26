package com.reza.appnikestore.feature.common

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reza.appnikestore.R
import com.reza.appnikestore.common.formatPrice
import com.reza.appnikestore.common.implementSpringAnimationTrait
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.services.ImageLoadingService
import com.reza.appnikestore.view.NikeImageView

const val VIEW_TYPE_ROUND = 0
const val VIEW_TYPE_SMALL = 1
const val VIEW_TYPE_LARGE = 2

class ProductListAdapter(
    var viewType: Int = VIEW_TYPE_ROUND,
    val imageLoadingService: ImageLoadingService
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var productEventListener: ProductEventListener? = null
    var products = ArrayList<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /*inner class ViewHolder(private val binding: ItemProductBinding) :
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
    }*/
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productIv: NikeImageView = itemView.findViewById(R.id.productIv)
        val titleTv: TextView = itemView.findViewById(R.id.productTitleTv)
        val currentPriceTv: TextView = itemView.findViewById(R.id.currentPriceTv)
        val previousPriceTv: TextView = itemView.findViewById(R.id.previousPriceTv)
        val favoriteBtn: ImageView = itemView.findViewById(R.id.favoriteBtn)
        fun bindProduct(product: Product) {
            imageLoadingService.load(productIv, product.image)
            titleTv.text = product.title
            currentPriceTv.text = formatPrice(product.price)
            previousPriceTv.text = formatPrice(product.previous_price)
            previousPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener {
                productEventListener?.onProductClick(product)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResId = when (viewType) {
            VIEW_TYPE_ROUND -> R.layout.item_product
            VIEW_TYPE_SMALL -> R.layout.item_product_small
            VIEW_TYPE_LARGE -> R.layout.item_product_large
            else -> throw IllegalStateException("viewType is not valid")
        }
        /* val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return ViewHolder(binding)*/
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    override fun getItemCount(): Int = products.size

    interface ProductEventListener {
        fun onProductClick(product: Product)
    }
}