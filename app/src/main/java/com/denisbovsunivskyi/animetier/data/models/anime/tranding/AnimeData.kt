package com.denisbovsunivskyi.animetier.data.models.anime.tranding


import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("data")
    val data: List<AnimeDto>
)