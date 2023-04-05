package com.ghitatomy.yooxshop.domain.usecase

import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.model.DomainProductDetails
import com.ghitatomy.yooxshop.domain.repository.ItemsRepository
import javax.inject.Inject

class GetItemDetailsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository
) {
    suspend fun execute(code: String) : DomainResponse<DomainProductDetails, ErrorResponse> {
        return itemsRepository.getItemDetails(code)
    }
}