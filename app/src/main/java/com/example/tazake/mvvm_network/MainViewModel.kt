package com.example.tazake.mvvm_network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tazake.domain.usecase.GetUsersUseCase
import com.example.tazake.network.dao.Reqres
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _article = MutableLiveData<Reqres?>()
    private val article: LiveData<Reqres?> = _article

    fun onClick() {
        viewModelScope.launch {
            try {
                Log.d("DEBUG", "============== fetch")
                _article.value = getUsersUseCase(1).confirmApiError()
                Log.d("DEBUG", "=success=============" + article.value.toString())
            } catch (e: HttpException) {
                Log.d("DEBUG", "=fail=============" + e.code() + ":" + e.message)
            } catch (t: Throwable) {
                Log.d("DEBUG", "=fail=============" + t.message)
            }
        }
    }
}