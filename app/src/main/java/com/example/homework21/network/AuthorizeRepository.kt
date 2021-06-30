package com.example.homework21.network

import com.example.homework21.model.Login
import com.example.homework21.model.Register

interface AuthorizeRepository {
    suspend fun login(email: String, password: String): ResultHandler<Login>

    suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): ResultHandler<Register>
}