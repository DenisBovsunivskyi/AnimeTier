package com.denisbovsunivskyi.animetier.data.models.anime.tranding


import com.google.gson.annotations.SerializedName

data class CoverImage(
    @SerializedName("original")
    val original: String
)