package com.denisbovsunivskyi.animetier.data.models.anime

import com.google.gson.annotations.SerializedName

class AnimePagingResponce<T>(
    @SerializedName("links")
    val links:Links?,
    @SerializedName("data")
    val data: List<T>
)