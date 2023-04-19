package com.denisbovsunivskyi.animetier.presentation.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.INVISIBLE
}
fun View.goneView() {
    visibility = View.GONE
}
fun View.showKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.setHideKeyboardWrapperListener() {
    this.setOnFocusChangeListener { view, hasFocus ->
        if (hasFocus) {
            view.hideKeyboard()
        }
    }
}

fun View.setOnClickListenerWithDebounce(delay: Long = 1000, action: () -> Unit) {
    this.setOnClickListener {
        this.isEnabled = false
        this.postDelayed({ this.isEnabled = true }, delay)
        action.invoke()
    }
}
