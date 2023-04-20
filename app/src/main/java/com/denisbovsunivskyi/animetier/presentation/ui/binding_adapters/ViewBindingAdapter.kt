package com.denisbovsunivskyi.animetier.presentation.ui.binding_adapters

import android.view.View
import androidx.databinding.BindingAdapter
object ViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("gone")
    fun setGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}