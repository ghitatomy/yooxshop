package com.ghitatomy.yooxshop.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.ghitatomy.yooxshop.R
import com.ghitatomy.yooxshop.databinding.FragmentProductInfoBinding
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.presentation.adapters.ColorListAdapter
import com.ghitatomy.yooxshop.presentation.adapters.SizeListAdapter
import com.ghitatomy.yooxshop.presentation.model.ProductDetailUIModel
import com.ghitatomy.yooxshop.presentation.model.ProductUIModel
import com.ghitatomy.yooxshop.presentation.viewmodels.ProductInfoViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductInfoFragment : Fragment() {
    private val viewModel : ProductInfoViewModel by viewModels()
    private lateinit var fragmentProductInfoBinding: FragmentProductInfoBinding

    private val colorClickListener = object : ColorListAdapter.ClickListener {
        override fun onColorButtonClick(color: String) { }
    }

    private val colorListAdapter: ColorListAdapter by lazy {
        ColorListAdapter(mutableListOf(), colorClickListener)
    }

    private val sizeClickListener = object : SizeListAdapter.ClickListener {
        override fun onSizeButtonClick() { }
    }

    private val sizeListAdapter: SizeListAdapter by lazy {
        SizeListAdapter(mutableListOf(), sizeClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentProductInfoBinding = FragmentProductInfoBinding.bind(view)
        val args : ProductInfoFragmentArgs by navArgs()
        val product = args.selectedProduct
        setupColorsList()
        setupSizesList()
        getProductDetails(product)
        viewLifecycleOwnerLiveData.value?.let { owner ->
            viewModel.productDetails.observe(owner) { response ->
                when (response) {
                    is DomainResponse.Success -> {
                        bindData(response.model)
                        hideProgressScroll()
                        fragmentProductInfoBinding.productInfoContainer.visibility = View.VISIBLE
                    }
                    is DomainResponse.Error -> {
                        hideProgressScroll()
                        response.model.message?.let {
                            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupColorsList() {
        fragmentProductInfoBinding.colorsRecyclerView.layoutManager =
            FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
                flexWrap = FlexWrap.WRAP
            }
        fragmentProductInfoBinding.colorsRecyclerView.adapter = colorListAdapter
    }

    private fun setupSizesList() {
        fragmentProductInfoBinding.sizesRecyclerView.layoutManager =
            FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
                flexWrap = FlexWrap.WRAP
            }
        fragmentProductInfoBinding.sizesRecyclerView.adapter = sizeListAdapter
    }

    private fun getProductDetails(product: ProductUIModel) {
        showProgressScroll()
        viewModel.getItemDetails(product)
    }

    private fun bindData(model: ProductDetailUIModel) {
        with(model) {
            with(fragmentProductInfoBinding) {
                Glide.with(productImageView.context)
                    .load(productImage)
                    .into(productImageView)
                brandNameTextView.text = brandName
                categoryNameTextView.text = categoryName
                priceTextView.text = price
                productInfoTextView.text = productInfo
                colorListAdapter.addColorList(colors)
                sizeListAdapter.addSizeList(sizes)
            }
        }
    }

    private fun showProgressScroll(){
        fragmentProductInfoBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressScroll(){
        fragmentProductInfoBinding.progressBar.visibility = View.INVISIBLE
    }
}