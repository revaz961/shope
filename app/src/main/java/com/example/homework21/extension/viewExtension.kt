package com.example.homework21.extension

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun View.showIf(isValid: Boolean) {
    if (isValid)
        visibility = View.VISIBLE
    else
        visibility = View.INVISIBLE
}