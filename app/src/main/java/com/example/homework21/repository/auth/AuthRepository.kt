package com.example.homework21.repository.auth

import com.example.homework21.model.ComplateProfileStatus
import com.example.homework21.model.Login
import com.example.homework21.model.ProfileCompleted
import com.example.homework21.model.Register
import com.example.homework21.network.ResultHandler
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String, rememberMe: Boolean): ResultHandler<Login>

    suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): ResultHandler<Register>

    suspend fun completeProfile(id: Int): ResultHandler<ComplateProfileStatus>
}