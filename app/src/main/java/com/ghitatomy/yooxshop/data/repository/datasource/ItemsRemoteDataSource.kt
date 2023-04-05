package com.ghitatomy.yooxshop.data.repository.datasource

import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.model.DomainProduct
import com.ghitatomy.yooxshop.domain.model.DomainProductDetails

interface ItemsRemoteDataSource {
    suspend fun getAllItems(itemSortType: ItemSortType) : DomainResponse<List<DomainProduct>, ErrorResponse>
    suspend fun getItemDetails(code: String) : DomainResponse<DomainProductDetails, ErrorResponse>
}