package com.example.tazake.mvvm_network

import com.example.tazake.domain.repository.UsersRepository
import com.example.tazake.domain.usecase.GetUsersUseCase
import com.example.tazake.network.ApiResult
import com.example.tazake.network.dao.Reqres
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private val mockRepository = mockk<UsersRepository>()
    private val getUsersUseCase = GetUsersUseCase(mockRepository)


    private lateinit var requres: Reqres

    @Before
    fun setup() {
        // JSON文字列
        val jsonString = """
        {
            "page": 2,
            "per_page": 3,
            "total": 12,
            "total_pages": 4,
            "data": [
                {
                    "id": 4,
                    "email": "eve.holt@reqres.in",
                    "first_name": "Eve",
                    "last_name": "Holt",
                    "avatar": "https://reqres.in/img/faces/4-image.jpg"
                },
                {
                    "id": 5,
                    "email": "charles.morris@reqres.in",
                    "first_name": "Charles",
                    "last_name": "Morris",
                    "avatar": "https://reqres.in/img/faces/5-image.jpg"
                },
                {
                    "id": 6,
                    "email": "tracey.ramos@reqres.in",
                    "first_name": "Tracey",
                    "last_name": "Ramos",
                    "avatar": "https://reqres.in/img/faces/6-image.jpg"
                }
            ],
            "support": {
                "url": "https://reqres.in/#support-heading",
                "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
            }
        }
    """.trimIndent()

        requres = Json.decodeFromString(jsonString)
    }

    @Test
    fun `GetUsersUseCase should return correct data`() = runTest {
        val result = ApiResult.Success(requres)
        coEvery { mockRepository.get(1) } returns result

        val target = getUsersUseCase.execute(1)

        Assert.assertEquals(2, target?.page)
        Assert.assertEquals(3, target?.perPage)
        Assert.assertEquals(12, target?.total)
        Assert.assertEquals(4, target?.totalPages)
        Assert.assertEquals(3, target?.data?.size)
    }


    @Test
    fun `GetUsersUseCase should return httpException`() = runTest {
        coEvery { mockRepository.get(1) }.throws(IOException())

        try {
            getUsersUseCase.execute(1)
        } catch (e: Exception) {
            Assert.assertTrue(e is IOException)
        }
    }

}