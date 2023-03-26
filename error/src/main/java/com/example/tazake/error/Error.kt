package com.example.tazake.error

import android.content.Context
import android.view.View
import retrofit2.HttpException

sealed class Error {

    abstract fun execute()

    class HttpError(val statusCode: HttpStatusCode, val message: String?) :
        Error() {
        override fun execute() {
            TODO("Not yet implemented")
        }

        override fun equals(other: Any?): Boolean = other is HttpError
    }

    class UnknownError(val view: View, throwable: Throwable) : Error() {
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    companion object {
        fun convert(
            context: Context, view: View, throwable: Throwable
        ): Error =
            when ( //        throwable is HttpException && throwable.code() == 503 -> MaintenanceError(context)
                throwable) {
                is HttpException -> HttpError(
                    HttpStatusCode.typeOf(throwable.code()),
                    throwable.message
                )
                else -> UnknownError(view, throwable)
            }
    }
}