package com.denisbovsunivskyi.animetier.data.datasource.anime

import com.denisbovsunivskyi.animetier.data.api.AnimeServiceApi
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeData
import retrofit2.Response
import javax.inject.Inject

class AnimeDataSourceImpl @Inject constructor(
    private val animeServiceApi: AnimeServiceApi
) : AnimeDataSource {
    override suspend fun fetchTrendingAnime(): Response<AnimeData> {
        return animeServiceApi.getTrendingAnime()
    }

    override suspend fun fetchAllAnime(): Response<AnimeData> {
        return animeServiceApi.getAllAnime()
    }

}
