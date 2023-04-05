package com.ghitatomy.yooxshop.data.model

import com.squareup.moshi.Json

data class ItemDetailsResponse(@Json(name = "Cod10")
                               val cod: String = "",
                               @Json(name = "Brand")
                               val brand: Brand,
                               @Json(name = "Sizes")
                               val sizes: List<SizesItem>?,
                               @Json(name = "Colors")
                               val colors: List<ColorsItem>?,
                               @Json(name = "Category")
                               val category: Category,
                               @Json(name = "ItemDescriptions")
                               val itemDescriptions: ItemDescriptions,
                               @Json(name = "FormattedPrice")
                               val formattedPrice: FormattedPrice)