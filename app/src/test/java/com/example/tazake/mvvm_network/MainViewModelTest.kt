package com.example.tazake.mvvm_network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tazake.domain.usecase.GetUsersUseCase
import com.example.tazake.network.dao.Reqres
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.*
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var result: Reqres

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)

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
        result = Json.decodeFromString(jsonString)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun addition_isCorrect() = runTest {

        val getUsersUseCase = mockk<GetUsersUseCase>()
        val target = MainViewModel(getUsersUseCase)

        coEvery { getUsersUseCase.execute(any()) } returns result

        target.onClick()
        val targetResult = target.reqres.value
        Assert.assertEquals(2, targetResult!!.page)
        Assert.assertEquals(12, targetResult!!.total)

    }
}