package com.ghitatomy.yooxshop.domain.model

import com.ghitatomy.yooxshop.data.model.ColorsItem
import com.ghitatomy.yooxshop.data.model.SizesItem

data class DomainProductDetails(
    var code: String,
    val brandName: String,
    val categoryName: String,
    val productInfo: List<String>,
    val colors: List<ColorsItem>,
    val sizes: List<SizesItem>,
    private val formattedFullPrice: String,
    private val formattedDiscountedPrice: String,
) {
    val productImage: String get() = DomainProduct.IMAGE_URL_PREFIX + code.take(2) + "/" + code + DomainProduct.IMAGE_URL_SUFFIX
    val price: String get() =
        if (formattedFullPrice.filter { it.isDigit() } == formattedDiscountedPrice.filter { it.isDigit() })
            formattedFullPrice
        else formattedDiscountedPrice
}