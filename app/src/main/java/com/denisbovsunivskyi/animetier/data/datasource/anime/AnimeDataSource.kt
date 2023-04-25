package com.denisbovsunivskyi.animetier.data.datasource.anime

import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeData
import retrofit2.Response

interface AnimeDataSource {
    suspend fun fetchTrendingAnime(): Response<AnimeData>
}