package com.example.homework21.repository.post

import com.example.homework21.model.Post
import com.example.homework21.network.ResultHandler

interface PostRepository {
    suspend fun getPost():ResultHandler<List<Post>>
}