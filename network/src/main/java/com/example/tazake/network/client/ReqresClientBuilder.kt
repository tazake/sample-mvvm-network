package com.example.tazake.network.client

import com.example.tazake.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


class ReqresClientBuilder(okHttpClient: OkHttpClient) {

    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_DOMAIN)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .client(okHttpClient)
        .build()

}


