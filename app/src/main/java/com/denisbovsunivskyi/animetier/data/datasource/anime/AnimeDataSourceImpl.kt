package com.denisbovsunivskyi.animetier.data.datasource.anime

import com.denisbovsunivskyi.animetier.data.api.AnimeServiceApi
import com.denisbovsunivskyi.animetier.data.models.anime.AnimeDataDto
import retrofit2.Response
import javax.inject.Inject

class AnimeDataSourceImpl @Inject constructor(
    private val animeServiceApi: AnimeServiceApi
) : AnimeDataSource {
    override suspend fun fetchTrendingAnime(): Response<AnimeDataDto> {
        return animeServiceApi.getTrendingAnime()
    }

    override suspend fun fetchAllAnime(): Response<AnimeDataDto> {
        return animeServiceApi.getAllAnime()
    }

}
