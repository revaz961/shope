package com.example.homework21.extension

import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setColorState(colorId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        hintTextColor = context.getColorStateList(colorId)
    }
}


fun TextView.setSpannedString(strings: Array<String>, colors: Array<Int>) {
    val spannableString = SpannableString(strings.joinToString(""))
    var startIndex = 0
    for (i in strings.indices) {
        spannableString.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(context, colors[i])
            ),
            startIndex, startIndex + strings[i].length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        startIndex += strings[i].length
    }
    text = spannableString
}