package com.example.homework21.extension

import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager

fun Dialog.init(
    view: View
) {
    this.window!!.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
    this.window!!.attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
    this.window!!.requestFeature(Window.FEATURE_NO_TITLE)
    this.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    this.setContentView(view)
}