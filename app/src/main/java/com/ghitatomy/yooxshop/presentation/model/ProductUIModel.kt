package com.ghitatomy.yooxshop.presentation.model
import java.io.Serializable

data class ProductUIModel (val code: String = "",
                           val brandName: String = "",
                           val category: String = "",
                           val price: String = "",
                           val imageUrl: String = "") : Serializable
