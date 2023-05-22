package com.denisbovsunivskyi.animetier.presentation.utils.extensions

import android.view.Window

fun Window.getSoftInputMode() : Int {
    return attributes.softInputMode
}