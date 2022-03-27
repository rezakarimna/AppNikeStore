package com.reza.appnikestore.feature.list

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.reza.appnikestore.R
import com.reza.appnikestore.common.EXTRA_KEY_DATA
import com.reza.appnikestore.common.NikeActivity
import com.reza.appnikestore.data.Product
import com.reza.appnikestore.databinding.ActivityProductListBinding
import com.reza.appnikestore.feature.common.ProductListAdapter
import com.reza.appnikestore.feature.common.VIEW_TYPE_LARGE
import com.reza.appnikestore.feature.common.VIEW_TYPE_SMALL
import com.reza.appnikestore.feature.product.ProductDetailActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductListActivity : NikeActivity(), ProductListAdapter.ProductEventListener {
    lateinit var binding: ActivityProductListBinding
    val productListAdapter: ProductListAdapter by inject { parametersOf(VIEW_TYPE_SMALL) }
    lateinit var gridLayoutManager: GridLayoutManager
    val viewModel: ProductListViewModel by viewModel {
        parametersOf(
            intent.extras!!.getInt(
                EXTRA_KEY_DATA
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeProgressbar()
        intiRecyclerView()
        observeProduct()
        viewTypeChanger()
        binding.sortBtn.setOnClickListener { createDialogAndSelectedSortTitle() }
        observeSelectedSortTitle()
        onBack()
    }

    private fun observeProgressbar() {
        viewModel.progressBarLiveData.observe(this) {
            setProgressIndicator(it)
        }
    }

    private fun intiRecyclerView() {
        gridLayoutManager = GridLayoutManager(this, 2)
        binding.productsRv.layoutManager = gridLayoutManager
        binding.productsRv.adapter = productListAdapter
        productListAdapter.productEventListener = this
    }

    private fun observeProduct() {
        viewModel.productsLiveData.observe(this) {
            productListAdapter.products = it as ArrayList<Product>
        }
    }

    private fun viewTypeChanger() {
        binding.viewTypeChangerBtn.setOnClickListener {
            if (productListAdapter.viewType == VIEW_TYPE_SMALL) {
                binding.viewTypeChangerBtn.setImageResource(R.drawable.ic_view_type_large)
                productListAdapter.viewType = VIEW_TYPE_LARGE
                gridLayoutManager.spanCount = 1
                productListAdapter.notifyDataSetChanged()
            } else {
                binding.viewTypeChangerBtn.setImageResource(R.drawable.ic_grid)
                productListAdapter.viewType = VIEW_TYPE_SMALL
                gridLayoutManager.spanCount = 2
                productListAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun createDialogAndSelectedSortTitle() {
        val dialog = MaterialAlertDialogBuilder(this)
            .setSingleChoiceItems(
                R.array.sortTitlesArray, viewModel.sort
            ) { dialog, selectedSortIndex ->
                viewModel.onSelectedSortChangedByUser(selectedSortIndex)
                dialog.dismiss()
            }.setTitle(getString(R.string.sort))
        dialog.show()
    }

    private fun observeSelectedSortTitle() {
        viewModel.selectedSortTitleLiveData.observe(this) {
            binding.selectedSortTitleTv.text = getString(it)
        }
    }

    private fun onBack() {
        binding.toolbarView.onBackButtonClickListener = View.OnClickListener {
            finish()
        }
    }

    override fun onProductClick(product: Product) {
        startActivity(Intent(this, ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, product)
        })
    }
}
