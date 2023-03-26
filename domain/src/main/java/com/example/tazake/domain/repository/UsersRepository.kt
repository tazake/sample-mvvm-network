package com.example.tazake.domain.repository

import com.example.tazake.network.dao.Reqres

interface UsersRepository {
    suspend fun get(
        pageCount: Int
    ): Reqres?
}