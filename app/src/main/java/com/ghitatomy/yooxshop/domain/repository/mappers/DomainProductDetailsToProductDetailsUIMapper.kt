package com.ghitatomy.yooxshop.domain.repository.mappers

import com.ghitatomy.yooxshop.domain.model.DomainProductDetails
import com.ghitatomy.yooxshop.presentation.model.ProductDetailUIModel
import javax.inject.Inject

class DomainProductDetailsToProductDetailsUIMapper @Inject constructor() {
    fun transform(domainProductDetails: DomainProductDetails) : ProductDetailUIModel =
        with(domainProductDetails) {
            ProductDetailUIModel(
                code = code,
                productImage = productImage,
                brandName = brandName,
                categoryName = categoryName,
                price = price,
                productInfo = productInfo.joinToString(separator = "\n"),
                colors = colors.map { it.rgb },
                sizes = sizes.map { it.name }
            )
        }
}