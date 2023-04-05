package com.ghitatomy.yooxshop.data.model

import com.squareup.moshi.Json

data class ItemDescriptions(@Json(name = "ProductInfo")
                            val productInfo: List<String>?)