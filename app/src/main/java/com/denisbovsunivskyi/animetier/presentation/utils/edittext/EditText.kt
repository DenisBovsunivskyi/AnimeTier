package com.denisbovsunivskyi.animetier.presentation.utils.edittext

import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionDoneListener
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyBackPressedListener


 
fun ClearFocusEditText.applyBackPressedListener(block: () -> Unit) {
    this.setOnBackPressedListener(object : OnKeyBackPressedListener {
        override fun onBackPressed() {
            block.invoke()
        }
    })
}

fun ClearFocusEditText.applyActionDonePressedListener(block: () -> Unit) {
    this.setOnActionDoneListener(object : OnKeyActionDoneListener {
        override fun onActionDone() {
            block.invoke()
        }
    })
}
