package com.denisbovsunivskyi.animetier.presentation.ui.binding_adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.denisbovsunivskyi.animetier.presentation.module.GlideApp


object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("setHorizontalAnimeImage")
    fun bindingSetHorizontalAnimePhoto(view: AppCompatImageView, path: String) {
        GlideApp.with(view)
            .asDrawable()
            .load(path)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
    @JvmStatic
    @BindingAdapter("setVerticalAnimeImage")
    fun bindingSetVerticalAnimePhoto(view: AppCompatImageView, path: String) {
        GlideApp.with(view)
            .asDrawable()
            .load(path)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
}
