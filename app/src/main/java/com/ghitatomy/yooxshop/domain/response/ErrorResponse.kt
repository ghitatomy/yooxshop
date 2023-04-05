package com.ghitatomy.yooxshop.domain.response

data class ErrorResponse(
    val errorCode: ErrorCode,
    val message: String? = null
)

sealed class ErrorCode {
    object ServerNotReachable : ErrorCode()
    object Unknown : ErrorCode()
}