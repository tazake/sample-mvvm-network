package com.example.tazake.domain.usecase

import com.example.tazake.domain.repository.UsersRepository
import com.example.tazake.network.dao.Reqres

class GetUsersUseCase(private val usersRepository: UsersRepository) {
    suspend fun execute(page: Int): Reqres = usersRepository.get(page).confirmApiError()
}