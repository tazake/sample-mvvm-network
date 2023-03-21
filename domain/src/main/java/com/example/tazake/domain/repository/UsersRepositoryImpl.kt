package com.example.tazake.domain.repository

import com.example.tazake.network.client.ReqresAPI
import com.example.tazake.network.dao.Reqres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UsersRepositoryImpl(private val reqresAPI: ReqresAPI) : UsersRepository {
    override suspend fun get(pageCount: Int): Response<Reqres> {
        return withContext(Dispatchers.IO) { reqresAPI.fetch() }
    }
}