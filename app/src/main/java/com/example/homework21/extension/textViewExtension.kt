package com.example.homework21.extension

import android.os.Build
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hintColor(colorId:Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        hintTextColor = context.getColorStateList(colorId)
    }
}