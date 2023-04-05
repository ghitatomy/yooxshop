package com.ghitatomy.yooxshop.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.usecase.GetItemDetailsUseCase
import com.ghitatomy.yooxshop.domain.repository.mappers.DomainProductDetailsToProductDetailsUIMapper
import com.ghitatomy.yooxshop.presentation.model.ProductDetailUIModel
import com.ghitatomy.yooxshop.presentation.model.ProductUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    private val getItemDetailsUseCase: GetItemDetailsUseCase,
    private val domainProductDetailsMapper: DomainProductDetailsToProductDetailsUIMapper
): ViewModel() {

    private val _productDetails: MutableLiveData<DomainResponse<ProductDetailUIModel, ErrorResponse>> = MutableLiveData()
    val productDetails: LiveData<DomainResponse<ProductDetailUIModel, ErrorResponse>> = _productDetails

    fun getItemDetails(product: ProductUIModel) {
        viewModelScope.launch {
            val response : DomainResponse<ProductDetailUIModel, ErrorResponse> =
                when (val apiResult =getItemDetailsUseCase.execute(product.code)) {
                    is DomainResponse.Success ->
                        DomainResponse.Success(domainProductDetailsMapper.transform(apiResult.model))
                    is DomainResponse.Error ->
                        DomainResponse.Error(apiResult.model)
                }
            _productDetails.postValue(response)
        }
    }
}