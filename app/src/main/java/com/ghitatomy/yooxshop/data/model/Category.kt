package com.ghitatomy.yooxshop.data.model

import com.squareup.moshi.Json

data class Category(@Json(name = "Name")
                    val name: String = "")