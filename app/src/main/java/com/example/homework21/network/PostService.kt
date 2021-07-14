package com.example.homework21.network

import com.example.homework21.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}