package com.denisbovsunivskyi.animetier.data.api

import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeData
import retrofit2.Response
import retrofit2.http.GET

interface AnimeServiceApi {
    companion object {
        const val GET_TRENDING_ANIME = "trending/anime"
        const val GET_ANIME = "anime"
    }

    @GET(GET_TRENDING_ANIME)
    suspend fun getTrendingAnime(): Response<AnimeData>
    @GET(GET_ANIME)
    suspend fun getAllAnime(): Response<AnimeData>
}