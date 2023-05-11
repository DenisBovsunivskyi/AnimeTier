package com.denisbovsunivskyi.animetier.data.models.anime

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("first")
    val first: String,
    @SerializedName("next")
    val next: String,
    @SerializedName("last")
    val last: String
)
