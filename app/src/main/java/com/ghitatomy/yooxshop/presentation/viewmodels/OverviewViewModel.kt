package com.ghitatomy.yooxshop.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.domain.response.ErrorResponse
import com.ghitatomy.yooxshop.domain.usecase.GetItemsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.repository.mappers.DomainProductsToProductsUIMapper
import com.ghitatomy.yooxshop.presentation.model.ProductUIModel
import kotlinx.coroutines.launch

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val getItemsListUseCase: GetItemsListUseCase,
    private val domainProductsToProductsUIMapper: DomainProductsToProductsUIMapper
) : ViewModel() {
    private val _itemsHeadlines: MutableLiveData<DomainResponse<List<ProductUIModel>, ErrorResponse>> = MutableLiveData()
    val itemsHeadlines: LiveData<DomainResponse<List<ProductUIModel>, ErrorResponse>> = _itemsHeadlines

    var previousSortType: ItemSortType = ItemSortType.RECOMMENDED
    var sortType: ItemSortType = ItemSortType.RECOMMENDED

    fun getItems() = viewModelScope.launch {
        val response : DomainResponse<List<ProductUIModel>, ErrorResponse> =
            when (val apiResult = getItemsListUseCase.execute(sortType)) {
                is DomainResponse.Success -> {
                    DomainResponse.Success(domainProductsToProductsUIMapper.transform(apiResult.model))
                }
                is DomainResponse.Error -> { DomainResponse.Error(apiResult.model) }
            }
        _itemsHeadlines.postValue(response)
    }

}