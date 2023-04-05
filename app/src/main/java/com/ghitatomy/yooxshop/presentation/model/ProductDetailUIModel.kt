package com.ghitatomy.yooxshop.presentation.model

data class ProductDetailUIModel (
    val code: String,
    val productImage: String,
    val brandName: String,
    val categoryName: String,
    val price: String,
    val productInfo: String,
    val colors: List<String>,
    val sizes: List<String>,
)
