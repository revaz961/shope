package com.example.homework21.extension

import android.content.res.ColorStateList
import android.os.Build
import com.example.homework21.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setColorState(colorId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        hintTextColor = context.getColorStateList(colorId)
    }
}

fun TextInputLayout.setIconEnd(id: Int) {
    endIconDrawable = context.getDrawable(id)
}