package com.example.homework21.screen.splash_screen

import androidx.lifecycle.ViewModel
import com.example.homework21.user_data.SessionData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val sessionData:SessionData) : ViewModel() {

    fun checkSession() = sessionData.hasSession()

}