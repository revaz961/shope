package com.example.homework21.model

import com.google.gson.annotations.SerializedName

data class Post(
    val id:Int,
    val owner:Int?,
    val title:String?,
    val description:String?,
    val category_id:Int?,
    val urls: List<String>?,
    val tags:String?,
    val price:Float?,
    @SerializedName("price_type")
    val priceType:String?
)