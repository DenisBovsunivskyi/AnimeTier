package com.denisbovsunivskyi.animetier.presentation.model.anime

import com.denisbovsunivskyi.animetier.data.models.anime.CoverImage
import com.denisbovsunivskyi.animetier.data.models.anime.PosterImage
import com.denisbovsunivskyi.animetier.data.models.anime.Titles
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeModel

data class AnimeListUi(
    val id: String,
    val titles: Titles,
    val episodesCount:Int?,
    val coverImage: CoverImage?,
    val posterImage:PosterImage?
)

fun AnimeModel.toUI() = AnimeListUi(
   id = this.id, titles = this.attributes.titles, episodesCount = this.attributes.episodeCount, coverImage = this.attributes.coverImage, posterImage = this.attributes.posterImage
)