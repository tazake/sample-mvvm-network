package com.example.tazake.domain.usecase

import com.example.tazake.domain.repository.UsersRepository
import com.example.tazake.network.dao.Reqres
import retrofit2.Response

class GetUsersUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(page: Int): Response<Reqres> = usersRepository.get(page)
}