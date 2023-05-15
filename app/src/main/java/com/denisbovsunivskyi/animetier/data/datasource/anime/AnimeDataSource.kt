package com.denisbovsunivskyi.animetier.data.datasource.anime

import com.denisbovsunivskyi.animetier.data.models.anime.AnimeDataDto
import retrofit2.Response

interface AnimeDataSource {
    suspend fun fetchTrendingAnime(): Response<AnimeDataDto>
    suspend fun fetchAllAnime(sortType:String?,status:String?): Response<AnimeDataDto>
}