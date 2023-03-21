package com.example.tazake.domain.repository

import com.example.tazake.network.dao.Reqres
import retrofit2.Response

interface UsersRepository {
    suspend fun get(
        pageCount: Int
    ): Response<Reqres>
}