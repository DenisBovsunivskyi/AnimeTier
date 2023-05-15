package com.denisbovsunivskyi.animetier.presentation.model.anime

import com.denisbovsunivskyi.animetier.data.models.anime.CoverImage
import com.denisbovsunivskyi.animetier.data.models.anime.PosterImage
import com.denisbovsunivskyi.animetier.data.models.anime.Titles
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeModel

data class AnimeListUi(
    val id: String,
    val titles: Titles,
    val episodesCount: Int?,
    val coverImage: CoverImage?,
    val posterImage: PosterImage?
) {
    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + titles.hashCode()
        result = 31 * result + titles.hashCode()
        result = 31 * result + episodesCount.hashCode()
        result = 31 * result + posterImage.hashCode()
        result = 31 * result + coverImage.hashCode()
        return result
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnimeListUi) return false

        if (id != other.id) return false
        if (titles != other.titles) return false
        if (episodesCount != other.episodesCount) return false

        return true
    }
}

fun AnimeModel.toUI() = AnimeListUi(
    id = this.id,
    titles = this.attributes.titles,
    episodesCount = this.attributes.episodeCount,
    coverImage = this.attributes.coverImage,
    posterImage = this.attributes.posterImage
)