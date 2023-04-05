package com.ghitatomy.yooxshop.data.repository.mapper

import com.ghitatomy.yooxshop.data.model.ItemsItem
import com.ghitatomy.yooxshop.domain.model.DomainProduct
import javax.inject.Inject

class ItemsItemToDomainProductMapper @Inject constructor() : BaseMapper<ItemsItem, DomainProduct> {
    override fun map(inModel: ItemsItem): DomainProduct = inModel.run {
        DomainProduct(
            brandName = brand,
            category = microCategory,
            code = cod,
            formattedFullPrice = formattedFullPrice,
            formattedDiscountedPrice = formattedDiscountedPrice
        )
    }

    override fun map(inList: List<ItemsItem>): List<DomainProduct> = inList.map { map(it) }

}