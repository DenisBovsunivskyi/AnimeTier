package com.denisbovsunivskyi.animetier.domain.models.anime

import com.denisbovsunivskyi.animetier.data.models.anime.CoverImage
import com.denisbovsunivskyi.animetier.data.models.anime.PosterImage
import com.denisbovsunivskyi.animetier.data.models.anime.RatingFrequencies
import com.denisbovsunivskyi.animetier.data.models.anime.Titles

data class Attributes(
    val abbreviatedTitles: List<String>,
    val ageRating: String?,
    val ageRatingGuide: String?,
    val averageRating: String,
    val canonicalTitle: String,
    val coverImage: CoverImage?,
    val coverImageTopOffset: Int,
    val createdAt: String,
    val description: String,
    val endDate: String?,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val favoritesCount: Int,
    val nextRelease: String?,
    val nsfw: Boolean,
    val popularityRank: Int,
    val posterImage: PosterImage,
    val ratingFrequencies: RatingFrequencies,
    val ratingRank: Int,
    val showType: String,
    val slug: String,
    val startDate: String,
    val status: String,
    val subtype: String,
    val synopsis: String,
    val tba: Any?,
    val titles: Titles,
    val totalLength: Int?,
    val updatedAt: String,
    val userCount: Int,
    val youtubeVideoId: String
)
