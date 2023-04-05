package com.ghitatomy.yooxshop.data.model

import com.squareup.moshi.Json

data class FormattedPrice(@Json(name = "FullPrice")
                          val fullPrice: String = "",
                          @Json(name = "DiscountedPrice")
                          val discountedPrice: String = "")