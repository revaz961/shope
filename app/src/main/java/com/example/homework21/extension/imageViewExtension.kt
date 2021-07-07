package com.example.homework21.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.homework21.R

fun ImageView.load(url: String?, placeholder:Int = R.drawable.image_not_found, error: Int= R.drawable.image_not_found) {
    Glide.with(this.context)
        .load(url?:"")
        .placeholder(placeholder)
        .error(error)
        .into(this);
}