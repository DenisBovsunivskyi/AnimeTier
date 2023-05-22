package com.denisbovsunivskyi.animetier.presentation.utils.edittext

import android.text.Editable
import android.text.TextWatcher
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionDoneListener
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionSearchListener


fun ClearFocusEditText.applyActionDonePressedListener(block: () -> Unit) {
    this.setOnActionDoneListener(object : OnKeyActionDoneListener {
        override fun onActionDone() {
            block.invoke()
        }
    })
}
fun ClearFocusEditText.applyActionSearchPressedListener(block: () -> Unit) {
    this.setOnActionSearchListener(object : OnKeyActionSearchListener {
        override fun onActionSearch() {
            block.invoke()
        }
    })
}
inline fun ClearFocusEditText.onTextChange(crossinline listener: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //NO OP
        }

        override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            listener(charSequence.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
            //NO OP
        }

    })
}