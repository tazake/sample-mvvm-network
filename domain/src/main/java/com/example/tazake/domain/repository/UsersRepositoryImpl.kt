package com.example.tazake.domain.repository

import com.example.tazake.network.ApiResult
import com.example.tazake.network.client.ReqresAPI
import com.example.tazake.network.dao.Reqres
import com.example.tazake.network.toApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepositoryImpl(private val reqresAPI: ReqresAPI) : UsersRepository {
    override suspend fun get(pageCount: Int): ApiResult<Reqres> {
        return withContext(Dispatchers.IO) { reqresAPI.fetch().toApiResult() }
    }
}