package com.example.tazake.network.client

import com.example.tazake.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


class ReqresClientBuilder(okHttpClient: OkHttpClient) {

    private val contentType = "application/json".toMediaType()
    private val format = Json { ignoreUnknownKeys = true }
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_DOMAIN)
        .addConverterFactory(format.asConverterFactory(contentType))
        .client(okHttpClient)
        .build()

}


