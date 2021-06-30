package com.example.homework21.network

import com.example.homework21.model.Login
import com.example.homework21.model.Register
import retrofit2.Response
import retrofit2.http.Field

interface ApiHelper {
    suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): Response<Register>

    suspend fun login(
        email: String,
        password: String,
    ): Response<Login>
}