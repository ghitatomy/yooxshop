package com.ghitatomy.yooxshop.domain.response

sealed class DomainResponse<out SUCCESS, out ERROR> {
    data class Success<out SUCCESS, out ERROR>(val model: SUCCESS): DomainResponse<SUCCESS, ERROR>()
    data class Error<out SUCCESS, out ERROR>(val model: ERROR): DomainResponse<SUCCESS, ERROR>()
}