package com.ghitatomy.yooxshop.data.repository.mapper

import com.ghitatomy.yooxshop.data.model.ItemDetailsResponse
import com.ghitatomy.yooxshop.domain.model.DomainProductDetails

class ItemDetailsToDomainProductDetailsMapper : BaseMapper<ItemDetailsResponse, DomainProductDetails> {
    override fun map(inModel: ItemDetailsResponse): DomainProductDetails = inModel.run {
        DomainProductDetails(
            brandName = brand.name,
            categoryName = category.name,
            code = cod,
            formattedFullPrice = formattedPrice.fullPrice,
            formattedDiscountedPrice = formattedPrice.discountedPrice,
            productInfo = itemDescriptions.productInfo ?: emptyList(),
            colors = colors ?: emptyList(),
            sizes = sizes ?: emptyList()
        )
    }

    override fun map(inList: List<ItemDetailsResponse>): List<DomainProductDetails> = inList.map { map(it) }
}