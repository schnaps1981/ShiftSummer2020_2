package com.shiftsummer2020_2.screens.main.domain.apiwrapper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> apiCallWrapper(apiCall: suspend () -> T): ApiResultWrapper<T> {
    return withContext(Dispatchers.IO)
    {
        try {
            ApiResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ApiResultWrapper.NetworkError
                is HttpException -> ApiResultWrapper.ApiError(throwable.code())
                else -> ApiResultWrapper.ApiError(null)
            }
        }
    }
}