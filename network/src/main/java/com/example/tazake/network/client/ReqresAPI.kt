package com.example.tazake.network.client

import com.example.tazake.network.dao.Reqres
import retrofit2.Response
import retrofit2.http.GET

interface ReqresAPI {
    @GET("users?page=1")
    suspend fun fetch(): Response<Reqres>
}