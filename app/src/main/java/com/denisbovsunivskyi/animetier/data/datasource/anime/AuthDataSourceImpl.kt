package com.denisbovsunivskyi.animetier.data.datasource.anime

import com.denisbovsunivskyi.animetier.data.api.AnimeServiceApi
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.TrendingAnimeData
import retrofit2.Response
import javax.inject.Inject

class AnimeDataSourceImpl @Inject constructor(
    private val animeServiceApi: AnimeServiceApi
) : AnimeDataSource {
    override suspend fun fetchTrendingAnime(): Response<TrendingAnimeData> {
        return animeServiceApi.getTrendingAnime()
    }

}
