package com.example.homework21.screen.auth.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework21.model.ComplateProfileStatus
import com.example.homework21.model.Login
import com.example.homework21.model.ProfileCompleted
import com.example.homework21.repository.auth.AuthRepository
import com.example.homework21.network.ResultHandler
import com.example.homework21.user_data.SessionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val userData:SessionData
) : ViewModel() {

    private val _loginLiveData = MutableLiveData<ResultHandler<Login>>()
    val loginLiveData: LiveData<ResultHandler<Login>> = _loginLiveData

    private val _profileLiveData = MutableLiveData<ResultHandler<ComplateProfileStatus>>()
    val profileLiveData: LiveData<ResultHandler<ComplateProfileStatus>> = _profileLiveData


    fun login(email: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _loginLiveData.postValue(ResultHandler.Loading(true))
                loginIn(email, password, rememberMe)
            }
        }
    }


    private suspend fun loginIn(email: String, password: String, rememberMe: Boolean) {
        val result = authRepo.login(email, password, rememberMe)
        _loginLiveData.postValue(result)
    }

    fun completeProfileStatus(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _loginLiveData.postValue(ResultHandler.Loading(true))
                _profileLiveData.postValue(authRepo.completeProfile(userData.getUserId()))
            }
        }
    }
}