package com.denisbovsunivskyi.animetier.data.api

import com.denisbovsunivskyi.animetier.data.models.anime.AnimeDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeServiceApi {
    companion object {
        const val GET_TRENDING_ANIME = "trending/anime"
        const val GET_ANIME = "anime"
    }

    @GET(GET_TRENDING_ANIME)
    suspend fun getTrendingAnime(): Response<AnimeDataDto>

    @GET(GET_ANIME)
    suspend fun getAllAnime(
        @Query("filter[text]") search:String? = null,
        @Query("page[limit]") limit: Int = 15,
        @Query("page[offset]") offset: Int = 0,
        @Query("filter[status]") status: String? = null,
        @Query("sort") sortType: String? = null,
        @Query("fields[anime]") fields: Array<String>? = arrayOf("slug,titles,episodeCount,canonicalTitle,posterImage,coverImage"),
    ): Response<AnimeDataDto>
}