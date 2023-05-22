package com.denisbovsunivskyi.animetier.data.models.anime


import com.google.gson.annotations.SerializedName

data class PosterImage(
    @SerializedName("original")
    val original: String,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("small")
    val small: String?
)