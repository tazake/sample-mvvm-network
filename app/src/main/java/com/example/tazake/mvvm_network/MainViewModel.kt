package com.example.tazake.mvvm_network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tazake.domain.usecase.GetUsersUseCase
import com.example.tazake.error.Error
import com.example.tazake.network.dao.Reqres
import kotlinx.coroutines.launch

class MainViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _reqres = MutableLiveData<Reqres>()
    val reqres: LiveData<Reqres> = _reqres

    fun onClick() {
        viewModelScope.launch {
            try {
                Log.d("DEBUG", "============== fetch")
                _reqres.value = getUsersUseCase.execute(1)
            } catch (t: Throwable) {
                Error.convert(throwable = t).execute()
            }
        }
    }
}