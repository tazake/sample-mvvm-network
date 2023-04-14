package com.example.tazake.mvvm_network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tazake.domain.repository.UsersRepositoryImpl
import com.example.tazake.domain.usecase.GetUsersUseCase
import com.example.tazake.mvvm_network.databinding.ActivityMainBinding
import com.example.tazake.network.client.ReqresAPI
import com.example.tazake.network.client.ReqresClient
import com.example.tazake.network.client.ReqresClientBuilder

class MainActivity : AppCompatActivity() {

    private val targetReqresAPI =
        ReqresClientBuilder(ReqresClient().http).create(ReqresAPI::class.java)

    private val usersRepository = UsersRepositoryImpl(targetReqresAPI)
    private val usersUseCase = GetUsersUseCase(usersRepository)
    private val viewModel = MainViewModel(usersUseCase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val mainViewModel: MainViewModel by viewModels()
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this

        viewModel.onClick()
    }
}