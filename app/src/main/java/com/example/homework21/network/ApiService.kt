package com.example.homework21.network

import com.example.homework21.model.Login
import com.example.homework21.model.Post
import com.example.homework21.model.Register
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("full_name") fullName: String
    ): Response<Register>


    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<Login>

    @GET("posts")
    suspend fun getPosts():Response<List<Post>>
}