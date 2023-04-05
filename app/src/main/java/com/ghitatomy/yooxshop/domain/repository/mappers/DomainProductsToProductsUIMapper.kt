package com.ghitatomy.yooxshop.domain.repository.mappers

import com.ghitatomy.yooxshop.domain.model.DomainProduct
import com.ghitatomy.yooxshop.presentation.model.ProductUIModel
import javax.inject.Inject

class DomainProductsToProductsUIMapper @Inject constructor() {
    fun transform(domainProductList: List<DomainProduct>) : List<ProductUIModel> =
        with(domainProductList) {
            this.map {
                ProductUIModel(
                    code = it.code,
                    brandName = it.brandName,
                    category = it.category,
                    price = it.price,
                    imageUrl = it.imageUrl,
                )
            }
        }
}
