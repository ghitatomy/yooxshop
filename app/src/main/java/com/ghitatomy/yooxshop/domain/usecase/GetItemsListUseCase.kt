package com.ghitatomy.yooxshop.domain.usecase

import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.model.DomainProduct
import com.ghitatomy.yooxshop.domain.repository.ItemsRepository
import javax.inject.Inject

class GetItemsListUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository
) {
    suspend fun execute(itemSortType: ItemSortType) : DomainResponse<List<DomainProduct>, ErrorResponse> {
        return itemsRepository.getAllItems(itemSortType)
    }
}