package com.example.tazake.network

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<*>.toApiResult(): ApiResult<T> {
    return if (this.isSuccessful) {
        ApiResult.Success(this.body() as T?)
    } else {
        ApiResult.Error(HttpException(this))
    }
}

sealed class ApiResult<T> {
    data class Success<T>(val data: T?) : ApiResult<T>()
    data class Error<T>(val httpException: HttpException) :
        ApiResult<T>()

    private val nullResult: T = null as T

    fun confirmApiError(): T {
        when (this) {
            is Success -> {
                return data ?: nullResult
            }
            is Error -> {
                throw httpException
            }
        }
    }
}