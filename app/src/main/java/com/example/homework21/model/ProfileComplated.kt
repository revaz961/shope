package com.example.homework21.model

import com.google.gson.annotations.SerializedName

data class ProfileCompleted(
    val OK: Boolean,
    @SerializedName("profile completed")
    val profileCompleted: Boolean
)