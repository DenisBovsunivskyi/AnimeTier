package com.denisbovsunivskyi.animetier.data.models.anime

import com.denisbovsunivskyi.animetier.data.models.DataLayerMapper
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeModel
import com.google.gson.annotations.SerializedName

data class AnimeModelDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("attributes")
    val attributes: AttributesDto,

    ) : DataLayerMapper<AnimeModel> {
    override fun toDomain(): AnimeModel {
        return AnimeModel(
            id = this.id,
            attributes = this.attributes.toDomain()
        )
    }
}
