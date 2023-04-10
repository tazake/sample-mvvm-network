package com.example.tazake.mvvm_network

import androidx.lifecycle.Observer
import com.example.tazake.domain.usecase.GetUsersUseCase
import com.example.tazake.network.dao.Reqres
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {

    private lateinit var reqres: Reqres

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

        // JSON文字列をデシリアライズして、Reqresクラスのインスタンスを生成
        reqres = Json.decodeFromString(jsonString)

    }

    @Test
    fun addition_isCorrect() {
        val getUsersUseCase = mockk<GetUsersUseCase>()
        coEvery { getUsersUseCase.getProperty(any()) } returns reqres
        val target = MainViewModel(getUsersUseCase)

        val mockObserver = mockk<Observer<Reqres?>>(relaxed = true)
        target.reqres.observeForever(mockObserver)

        target.onClick()
        verify(exactly = 1) {
            mockObserver.onChanged(reqres)
        }
    }
}