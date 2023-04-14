package com.example.tazake.network.client

import com.example.tazake.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class ReqresClient {
    val http: OkHttpClient

    init {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = BuildConfig.HTTP_LOG_LEVEL
        builder.addInterceptor(logging)

        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(10, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        http = builder.build()
    }

}