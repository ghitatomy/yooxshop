package com.ghitatomy.yooxshop.domain.repository

import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.model.DomainProduct
import com.ghitatomy.yooxshop.domain.model.DomainProductDetails

interface ItemsRepository {
    suspend fun getAllItems(itemSortType: ItemSortType) : DomainResponse<List<DomainProduct>, ErrorResponse>
    suspend fun getItemDetails(code: String) : DomainResponse<DomainProductDetails, ErrorResponse>
}