package com.denisbovsunivskyi.animetier.presentation.utils

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo

fun isEnterPressed(keyEvent: KeyEvent?): Boolean {
    return keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER ||
            keyEvent?.keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER
}

fun isDonePressed(key: Int?): Boolean {
    return key == EditorInfo.IME_ACTION_DONE
}

fun isNextPressed(key: Int?): Boolean {
    return key == EditorInfo.IME_ACTION_NEXT
}
fun isSearchPressed(key: Int?): Boolean {
    return key == EditorInfo.IME_ACTION_SEARCH
}
