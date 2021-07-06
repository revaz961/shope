package com.example.homework21.repository.auth

import com.example.homework21.model.ErrorResult
import com.example.homework21.model.Login
import com.example.homework21.model.Register
import com.example.homework21.network.ApiService
import com.example.homework21.network.ResultHandler
import com.example.homework21.user_data.SessionData
import com.google.gson.Gson
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sessionInfo: SessionData
) :
    AuthRepository {
    override suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean
    ): ResultHandler<Login> {
        return try {
            val response = apiService.login(email, password)
            val body = response.body()
            return if (response.isSuccessful) {
                if(rememberMe){
                    sessionInfo.saveSession(true)
                    sessionInfo.saveToken(body?.token!!)
                }
                ResultHandler.Success(body)
            }
            else {
                val errorResult = Gson().fromJson(response.errorBody()!!.string(), ErrorResult::class.java)
                ResultHandler.Error(body, errorResult?.error ?: "")
            }
        } catch (e: Exception) {
            ResultHandler.Error(null, e.message.toString())
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): ResultHandler<Register> {
        return try {
            val response = apiService.register(email, password, fullName)
            val body = response.body()
            if (response.isSuccessful)
                ResultHandler.Success(body)
            else {
                val errorResult = Gson().fromJson(response.errorBody()!!.string(), ErrorResult::class.java)
                ResultHandler.Error(body, errorResult?.error ?: "")
            }
        } catch (e: Exception) {
            ResultHandler.Error(null, e.message.toString())
        }
    }
}