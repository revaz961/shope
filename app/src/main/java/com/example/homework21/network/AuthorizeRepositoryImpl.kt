package com.example.homework21.network

import com.example.homework21.model.Login
import com.example.homework21.model.Register
import javax.inject.Inject

class AuthorizeRepositoryImpl @Inject constructor(private val apiHelper: ApiHelper) :
    AuthorizeRepository {
    override suspend fun login(email: String, password: String): ResultHandler<Login> {
        try {
            val response = apiHelper.login(email, password)
            val body = response.body()
            return if (response.isSuccessful)
                ResultHandler.Success(response.body()!!)
            else
                ResultHandler.Error(response.body()!!, response.errorBody()!!.string())
        }catch (e:Exception){
            return ResultHandler.Error(null,"some error")
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): ResultHandler<Register> {
        val response = apiHelper.register(email, password, fullName)
        return if (response.isSuccessful)
            ResultHandler.Success(response.body()!!)
        else
            ResultHandler.Error(response.body()!!, response.errorBody()!!.string())
    }


}