package com.shiftsummer2020_2.screens.main.domain.apiwrapper

sealed class ApiResultWrapper<out T> {
    data class Success<out T>(val result: T) : ApiResultWrapper<T>()
    data class ApiError(val code: Int? = null): ApiResultWrapper<Nothing>()
    data class OtherError(val message: String? = "") : ApiResultWrapper<Nothing>()
    object NetworkError: ApiResultWrapper<Nothing>()
}