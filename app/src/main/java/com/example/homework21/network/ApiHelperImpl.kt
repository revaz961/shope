package com.example.homework21.network


import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun register(
        email: String,
        password: String,
        fullName: String
    ) = apiService.register(email, password, fullName)

    override suspend fun login(email: String, password: String) = apiService.login(email, password)
}