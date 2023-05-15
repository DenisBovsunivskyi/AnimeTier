package com.denisbovsunivskyi.animetier.data.models.anime


import com.denisbovsunivskyi.animetier.data.models.DataLayerMapper
import com.denisbovsunivskyi.animetier.domain.models.anime.Attributes
import com.google.gson.annotations.SerializedName

data class AttributesDto(
    @SerializedName("abbreviatedTitles")
    val abbreviatedTitles: List<String>?,
    @SerializedName("ageRating")
    val ageRating: String?,
    @SerializedName("ageRatingGuide")
    val ageRatingGuide: String?,
    @SerializedName("averageRating")
    val averageRating: String?,
    @SerializedName("canonicalTitle")
    val canonicalTitle: String,
    @SerializedName("coverImage")
    val coverImage: CoverImage,
    @SerializedName("coverImageTopOffset")
    val coverImageTopOffset: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("endDate")
    val endDate: String?,
    @SerializedName("episodeCount")
    val episodeCount: Int?,
    @SerializedName("episodeLength")
    val episodeLength: Int?,
    @SerializedName("favoritesCount")
    val favoritesCount: Int?,
    @SerializedName("nextRelease")
    val nextRelease: String?,
    @SerializedName("nsfw")
    val nsfw: Boolean?,
    @SerializedName("popularityRank")
    val popularityRank: Int?,
    @SerializedName("posterImage")
    val posterImage: PosterImage,
    @SerializedName("ratingFrequencies")
    val ratingFrequencies: RatingFrequencies,
    @SerializedName("ratingRank")
    val ratingRank: Int?,
    @SerializedName("showType")
    val showType: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("startDate")
    val startDate: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("subtype")
    val subtype: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("tba")
    val tba: Any?,
    @SerializedName("titles")
    val titles: Titles,
    @SerializedName("totalLength")
    val totalLength: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("userCount")
    val userCount: Int?,
    @SerializedName("youtubeVideoId")
    val youtubeVideoId: String?
) : DataLayerMapper<Attributes> {
    override fun toDomain(): Attributes {
        return Attributes(
            abbreviatedTitles = this.abbreviatedTitles,
            ageRating = this.ageRating,
            ageRatingGuide = this.ageRatingGuide,
            averageRating = this.averageRating,
            canonicalTitle = this.canonicalTitle,
            coverImage = this.coverImage,
            coverImageTopOffset = this.coverImageTopOffset,
            createdAt = this.createdAt,
            description = this.description,
            endDate = this.endDate,
            episodeCount = this.episodeCount,
            episodeLength = this.episodeLength,
            favoritesCount = this.favoritesCount,
            nextRelease = this.nextRelease,
            nsfw = this.nsfw,
            popularityRank = this.popularityRank,
            posterImage = this.posterImage,
            ratingFrequencies = this.ratingFrequencies,
            ratingRank = this.ratingRank,
            showType = this.showType,
            slug = this.slug,
            startDate = this.startDate,
            status = this.status,
            subtype = this.subtype,
            synopsis = this.synopsis,
            tba = this.tba,
            titles = this.titles,
            totalLength = this.totalLength,
            updatedAt = this.updatedAt,
            userCount = this.userCount,
            youtubeVideoId = this.youtubeVideoId
        )
    }

}