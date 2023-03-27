package com.denisbovsunivskyi.animetier.presentation.utils

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.google.android.material.button.MaterialButton


fun MaterialButton.colorizeEnd(startString:String,endString:String,color:Int){
    val builder = SpannableStringBuilder()

    val str1 = SpannableString(startString)
    builder.append(str1)
    builder.append(" ")
    val str2 = SpannableString(endString)
    str2.setSpan(ForegroundColorSpan(color), 0, str2.length, 0)
    builder.append(str2)

   this.setText(builder, TextView.BufferType.SPANNABLE)
}