package com.ghitatomy.yooxshop.domain

enum class ItemSortType(val sortType: String, var page: Long) {
    RECOMMENDED("Recommended", 1) ,
    LATEST_ARRIVALS("Latest Arrivals", -1),
    LOW_PRICE("Low Price", -1),
    HIGH_PRICE("High Price", -1)
}