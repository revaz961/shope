package com.example.homework21.network

import com.example.homework21.model.Error
import com.example.homework21.model.Login
import com.example.homework21.model.Register
import com.example.homework21.user_data.SessionSharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class AuthorizeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sessionInfo: SessionSharedPreferences
) :
    AuthorizeRepository {
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
                    sessionInfo.saveToken(body!!.token!!)
                }
                ResultHandler.Success(body!!)
            }
            else {
                val errorModel = Gson().fromJson(response.errorBody()!!.string(), Error::class.java)
                ResultHandler.Error(body, errorModel.error)
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
                ResultHandler.Success(response.body()!!)
            else {
                val errorModel = Gson().fromJson(response.errorBody()!!.string(), Error::class.java)
                ResultHandler.Error(body, errorModel.error)
            }
        } catch (e: Exception) {
            ResultHandler.Error(null, e.message.toString())
        }
    }
}