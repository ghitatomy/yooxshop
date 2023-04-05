package com.ghitatomy.yooxshop.data.repository

import com.ghitatomy.yooxshop.data.repository.datasource.ItemsRemoteDataSource
import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.model.DomainProduct
import com.ghitatomy.yooxshop.domain.model.DomainProductDetails
import com.ghitatomy.yooxshop.domain.repository.ItemsRepository
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor (
    private val itemsRemoteDataSource: ItemsRemoteDataSource,
) : ItemsRepository {
    override suspend fun getAllItems(itemSortType: ItemSortType): DomainResponse<List<DomainProduct>, ErrorResponse> =
        itemsRemoteDataSource.getAllItems(itemSortType)

    override suspend fun getItemDetails(code: String): DomainResponse<DomainProductDetails, ErrorResponse> =
        itemsRemoteDataSource.getItemDetails(code)
}