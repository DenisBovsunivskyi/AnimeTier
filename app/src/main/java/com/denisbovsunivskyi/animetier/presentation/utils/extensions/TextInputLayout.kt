package com.denisbovsunivskyi.animetier.presentation.utils.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setErrorMsg(msg: String) {
    this.isErrorEnabled = true
    this.error = msg
}

fun TextInputLayout.clearError() {
    this.error = null
    this.isErrorEnabled = false
}
