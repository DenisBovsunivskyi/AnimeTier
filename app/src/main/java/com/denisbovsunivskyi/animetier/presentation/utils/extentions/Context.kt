package com.denisbovsunivskyi.animetier.presentation.utils.extentions

import android.content.Context
import android.widget.Toast

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}