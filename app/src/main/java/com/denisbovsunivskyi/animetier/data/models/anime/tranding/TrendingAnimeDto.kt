package com.denisbovsunivskyi.animetier.data.models.anime.tranding

import com.google.gson.annotations.SerializedName

data class TrendingAnimeDto(
    @SerializedName("id")
    val id:String,
    @SerializedName("attributes")
    val attributes: Attributes,

)
