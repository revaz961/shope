package com.example.homework21.ui.auth.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework21.model.Register
import com.example.homework21.repository.auth.AuthRepository
import com.example.homework21.network.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : ViewModel() {

    private val _registerLiveData = MutableLiveData<ResultHandler<Register>>()
    val registerLiveData: LiveData<ResultHandler<Register>> = _registerLiveData

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _registerLiveData.postValue(ResultHandler.Loading(true))
                registerUser(email,password,fullName)
            }
        }
    }

    private suspend fun registerUser(email: String, password: String, fullName: String) {
        val result = authRepo.register(email, password, fullName)
        _registerLiveData.postValue(result)
    }
}