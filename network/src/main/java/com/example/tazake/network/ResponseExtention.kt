package com.example.tazake.network

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<*>.toApiResult(): ApiResult<T> {

    if (this.isSuccessful) {
        if (this.body() != null) {
            @Suppress("UNCHECKED_CAST")
            return ApiResult.Success(this.body() as T)
        } else {
            return ApiResult.Error(HttpException(this))
        }
    } else {
        return ApiResult.Error(HttpException(this))
    }

}

sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error<T>(val httpException: HttpException) :
        ApiResult<T>()

    fun confirmApiError(): T {
        when (this) {
            is Success -> {
                return data
            }
            is Error -> {
                throw httpException
            }
        }
    }
}