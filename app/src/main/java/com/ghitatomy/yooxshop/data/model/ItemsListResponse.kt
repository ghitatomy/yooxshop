package com.ghitatomy.yooxshop.data.model

import com.squareup.moshi.Json

data class ItemsListResponse(@Json(name = "Items")
                             val items: List<ItemsItem>?)