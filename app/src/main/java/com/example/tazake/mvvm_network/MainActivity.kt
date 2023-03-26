package com.example.tazake.mvvm_network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tazake.domain.repository.UsersRepositoryImpl
import com.example.tazake.domain.usecase.GetUsersUseCase
import com.example.tazake.network.client.ReqresAPI
import com.example.tazake.network.client.ReqresClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private val contentType = "application/json".toMediaType()
    private val targetReqresAPI = Retrofit.Builder()
        .client(ReqresClient().http)
        .baseUrl("https://reqres.in/api/fdaefaedad/")
//        .baseUrl(SERVER_DOMAIN)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
        .create(ReqresAPI::class.java)

    private val usersRepository = UsersRepositoryImpl(targetReqresAPI)
    private val usersUseCase = GetUsersUseCase(usersRepository)
    private val viewModel = MainViewModel(usersUseCase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.onClick()
    }
}