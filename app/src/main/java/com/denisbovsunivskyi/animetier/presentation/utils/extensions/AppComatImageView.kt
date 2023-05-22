package com.denisbovsunivskyi.animetier.presentation.utils.extensions

import android.net.Uri
import androidx.appcompat.widget.AppCompatImageView
import com.denisbovsunivskyi.animetier.presentation.module.GlideApp

fun AppCompatImageView.loadNewImageFromUri(uri:Uri){
    GlideApp.with(this).load(uri).circleCrop().into(this)
}