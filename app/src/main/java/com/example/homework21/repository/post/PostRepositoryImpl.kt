package com.example.homework21.repository.post

import com.example.homework21.model.ErrorResult
import com.example.homework21.model.Post
import com.example.homework21.network.ApiService
import com.example.homework21.network.ResultHandler
import com.google.gson.Gson
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val apiService: ApiService) : PostRepository {
    override suspend fun getPost(): ResultHandler<List<Post>> {
        return try {
            val response = apiService.getPosts()
            val body = response.body()
            if (response.isSuccessful)
                ResultHandler.Success(body)
            else {
                val errorResult =
                    Gson().fromJson(response.errorBody()!!.string(), ErrorResult::class.java)
                ResultHandler.Error(body, errorResult?.error ?: "something wrong")
            }
        } catch (e: Exception) {
            ResultHandler.Error(null, e.message.toString())
        }
    }
}