package com.denisbovsunivskyi.animetier.data.models.anime


import com.google.gson.annotations.SerializedName

data class CoverImage(
    @SerializedName("original")
    val original: String,
    @SerializedName("medium")
    val medium:String
)