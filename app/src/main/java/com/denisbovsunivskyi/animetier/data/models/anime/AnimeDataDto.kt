package com.denisbovsunivskyi.animetier.data.models.anime


import com.denisbovsunivskyi.animetier.data.models.DataLayerMapper
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeData
import com.google.gson.annotations.SerializedName

data class AnimeDataDto(
    @SerializedName("data")
    val data: List<AnimeModelDto>
) : DataLayerMapper<AnimeData> {
    override fun toDomain(): AnimeData {
        return AnimeData(data.map { it.toDomain() })
    }

}