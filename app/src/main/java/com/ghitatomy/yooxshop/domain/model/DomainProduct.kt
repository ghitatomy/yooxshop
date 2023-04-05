package com.ghitatomy.yooxshop.domain.model

data class DomainProduct (val brandName: String,
                          val category: String,
                          val code: String,
                          private val formattedFullPrice: String,
                          private val formattedDiscountedPrice: String) {
    companion object {
        const val IMAGE_URL_PREFIX = "https://www.yoox.com/images/items/"
        const val IMAGE_URL_SUFFIX = "_13_f.jpg"
    }
    val price: String get() =
        if (formattedFullPrice.filter { it.isDigit() } == formattedDiscountedPrice.filter { it.isDigit() })
            formattedFullPrice
        else formattedDiscountedPrice

    val imageUrl: String get() = IMAGE_URL_PREFIX + code.take(2) + "/" + code + IMAGE_URL_SUFFIX
}