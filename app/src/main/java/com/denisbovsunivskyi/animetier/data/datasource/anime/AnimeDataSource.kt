package com.denisbovsunivskyi.animetier.data.datasource.anime

import com.denisbovsunivskyi.animetier.data.models.anime.tranding.TrendingAnimeData
import retrofit2.Response

interface AnimeDataSource {
    suspend fun fetchTrendingAnime(): Response<TrendingAnimeData>
}