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

fun View.goneIf(isValid:Boolean){
    if(isValid)
        gone()
    else
        show()
}

fun View.showIf(isValid: Boolean) {
    if (isValid)
        show()
    else
        hide()
}