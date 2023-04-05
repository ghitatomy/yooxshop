package com.ghitatomy.yooxshop.data.repository.datasourceimplementation

import com.ghitatomy.yooxshop.data.api.YooxApiService
import com.ghitatomy.yooxshop.data.repository.datasource.ItemsRemoteDataSource
import com.ghitatomy.yooxshop.data.repository.mapper.ItemDetailsToDomainProductDetailsMapper
import com.ghitatomy.yooxshop.data.repository.mapper.ItemsItemToDomainProductMapper
import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorCode
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.model.DomainProduct
import com.ghitatomy.yooxshop.domain.model.DomainProductDetails
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import kotlinx.coroutines.withContext

class ItemsRemoteDataSourceImpl(
    private val yooxApiService: YooxApiService,
    private val itemsItemToDomainProductMapper: ItemsItemToDomainProductMapper,
    private val itemDetailsToDomainProductDetailsMapper: ItemDetailsToDomainProductDetailsMapper
    ) : ItemsRemoteDataSource {
    override suspend fun getAllItems(itemSortType: ItemSortType): DomainResponse<List<DomainProduct>, ErrorResponse>
    = withContext(Dispatchers.IO) {
        try {
            val response = when (itemSortType) {
                ItemSortType.RECOMMENDED -> yooxApiService.getAllItems(itemSortType.page)
                ItemSortType.LATEST_ARRIVALS -> yooxApiService.getLatestItems()
                ItemSortType.LOW_PRICE -> yooxApiService.getLowPriceItems()
                ItemSortType.HIGH_PRICE -> yooxApiService.getHighPriceItems()
            }
            val mappedResponse = itemsItemToDomainProductMapper.map(response.items ?: emptyList())
            return@withContext DomainResponse.Success(mappedResponse)
        } catch (e: Exception) {
            val errorCode = when (e) {
                is IOException -> ErrorCode.ServerNotReachable
                else -> ErrorCode.Unknown
            }
            return@withContext DomainResponse.Error(ErrorResponse(errorCode, e.message))
        }
    }

    override suspend fun getItemDetails(code: String): DomainResponse<DomainProductDetails, ErrorResponse> = withContext(
        Dispatchers.IO) {
        try {
            val response = itemDetailsToDomainProductDetailsMapper.map(yooxApiService.getItemDetails()).apply { this.code = code }
            return@withContext DomainResponse.Success(response)
        } catch (e: Exception) {
            val errorCode = when (e) {
                is IOException -> ErrorCode.ServerNotReachable
                else -> ErrorCode.Unknown
            }
            return@withContext DomainResponse.Error(ErrorResponse(errorCode, e.message))
        }
    }
}