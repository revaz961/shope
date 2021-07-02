package com.example.homework21.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework21.model.Login
import com.example.homework21.model.Register
import com.example.homework21.network.AuthorizeRepository
import com.example.homework21.network.ResultHandler
import com.example.homework21.sessionpreferences.SessionSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepo: AuthorizeRepository,
    private val sessionInfo: SessionSharedPreferences
) :
    ViewModel() {

    private val _loginLiveData = MutableLiveData<ResultHandler<Login>>()
    val loginLiveData: LiveData<ResultHandler<Login>> = _loginLiveData

    private val _registerLiveData = MutableLiveData<ResultHandler<Register>>()
    val registerLiveData: LiveData<ResultHandler<Register>> = _registerLiveData

    fun login(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _loginLiveData.postValue(ResultHandler.Loading(true))
                loginIn(email, password)
                _loginLiveData.postValue(ResultHandler.Loading(false))
            }
        }
    }

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _registerLiveData.postValue(ResultHandler.Loading(true))
                registerUser(email,password,fullName)
                _registerLiveData.postValue(ResultHandler.Loading(false))
            }
        }
    }

    private suspend fun loginIn(email: String, password: String) {
        _loginLiveData.postValue(authRepo.login(email, password))
    }

    private suspend fun registerUser(email: String, password: String, fullName: String) {
        _registerLiveData.postValue(authRepo.register(email, password, fullName))
    }

    fun saveSession(inSession: Boolean) {
        sessionInfo.saveSession(inSession)
    }
}