package com.example.tazake.network

import com.example.tazake.network.client.ReqresAPI
import com.example.tazake.network.client.ReqresClient
import com.example.tazake.network.client.ReqresClientBuilder
import com.example.tazake.network.dao.Reqres
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.HttpURLConnection

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ReqresAPIUnitTest {

    private val mockWebServer: MockWebServer = MockWebServer()
    private lateinit var targetReqresAPI: ReqresAPI

    @Before
    fun setup() {
        mockWebServer.start()
        val contentType = "application/json".toMediaType()
        val format = Json { ignoreUnknownKeys = true }
        targetReqresAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(ReqresClient().http)
            .build()
            .create(ReqresAPI::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun base_url_isCorrect() {
        val target = ReqresClientBuilder(ReqresClient().http)
        assertEquals(BuildConfig.SERVER_DOMAIN, target.retrofit.baseUrl().toUrl().toString())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun reqresAPI_isSuccessful() = runTest {
        val testString =
            "{\"page\":1,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":[{\"id\":1,\"email\":\"george.bluth@reqres.in\",\"first_name\":\"George\",\"last_name\":\"Bluth\",\"avatar\":\"https://reqres.in/img/faces/1-image.jpg\"},{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},{\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\",\"last_name\":\"Wong\",\"avatar\":\"https://reqres.in/img/faces/3-image.jpg\"},{\"id\":4,\"email\":\"eve.holt@reqres.in\",\"first_name\":\"Eve\",\"last_name\":\"Holt\",\"avatar\":\"https://reqres.in/img/faces/4-image.jpg\"},{\"id\":5,\"email\":\"charles.morris@reqres.in\",\"first_name\":\"Charles\",\"last_name\":\"Morris\",\"avatar\":\"https://reqres.in/img/faces/5-image.jpg\"},{\"id\":6,\"email\":\"tracey.ramos@reqres.in\",\"first_name\":\"Tracey\",\"last_name\":\"Ramos\",\"avatar\":\"https://reqres.in/img/faces/6-image.jpg\"}],\"support\":{\"url\":\"https://reqres.in/#support-heading\",\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}"
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(testString)
        mockWebServer.enqueue(response)
        val target = targetReqresAPI.fetch().toApiResult<Reqres>().confirmApiError()

        assertEquals(1, target?.page)
        assertEquals(6, target?.perPage)
        assertEquals(12, target?.total)
        assertEquals(2, target?.totalPages)
        assertEquals(6, target?.data?.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun reqresAPI_isFailure() = runTest {
        val testString = "{\"error\": \"Not Found\"}"
        val response = MockResponse()
            .setResponseCode(403)
            .setBody(testString)
        mockWebServer.enqueue(response)
        try {
            targetReqresAPI.fetch().toApiResult<Reqres>().confirmApiError()
        } catch (e: HttpException) {
            assertEquals(403, e.code())
        }
    }

}

