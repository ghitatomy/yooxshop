package com.ghitatomy.yooxshop.data.model

import com.squareup.moshi.Json

data class ItemsItem(@Json(name = "FormattedDiscountedPrice")
                     val formattedDiscountedPrice: String = "",
                     @Json(name = "Cod10")
                     val cod: String = "",
                     @Json(name = "FormattedFullPrice")
                     val formattedFullPrice: String = "",
                     @Json(name = "MicroCategoryPlural")
                     val microCategoryPlural: String = "",
                     @Json(name = "Brand")
                     val brand: String = "",
                     @Json(name = "MicroCategory")
                     val microCategory: String = "")