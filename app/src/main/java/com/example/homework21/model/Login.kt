package com.example.homework21.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("userId")
    val user_id: Int, val token: String?
)
