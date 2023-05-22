package com.denisbovsunivskyi.animetier.presentation.ui.binding_adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.denisbovsunivskyi.animetier.data.models.anime.PosterImage
import com.denisbovsunivskyi.animetier.presentation.model.anime.AnimeListUi
import com.denisbovsunivskyi.animetier.presentation.module.GlideApp


object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("setHorizontalAnimeImage")
    fun bindingSetHorizontalAnimePhoto(view: AppCompatImageView, image: PosterImage?) {
        val imageUrl = image?.small ?: image?.medium ?: image?.original

        GlideApp.with(view)
            .asDrawable()
            .load(imageUrl)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setVerticalAnimeImage")
    fun bindingSetVerticalAnimePhoto(view: AppCompatImageView, image: PosterImage?) {
        val imageUrl = image?.small ?: image?.medium ?: image?.original
        GlideApp.with(view)
            .asDrawable()
            .load(imageUrl)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setLandscapeAnimeCoverImage")
    fun bindingSetLandscapeAnimeCoverImage(view: AppCompatImageView, anime: AnimeListUi) {
        val imageUrl =
            anime.coverImage?.original ?: anime.posterImage?.medium ?: anime.posterImage?.original

        GlideApp.with(view)
            .asDrawable()
            .load(imageUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)


    }
}
