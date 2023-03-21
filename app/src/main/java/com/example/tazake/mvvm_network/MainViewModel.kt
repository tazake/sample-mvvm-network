package com.example.tazake.mvvm_network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tazake.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    fun onClick() {
        viewModelScope.launch {
            getUsersUseCase(1)
        }
    }
}