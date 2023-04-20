package com.denisbovsunivskyi.animetier.data.repository.anime

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.TrendingAnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.TrendingAnimeRepository
import retrofit2.Response

class TrendingAnimeRepositoryImpl(
    private val animeDataSource: AnimeDataSource
) : TrendingAnimeRepository {
    override suspend fun fetchTrendingAnime(): ResponseState<TrendingAnimeData, UniversalText> {
        return responseToRequest(animeDataSource.fetchTrendingAnime())
    }

    private fun responseToRequest(response: Response<TrendingAnimeData>): ResponseState<TrendingAnimeData, UniversalText> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return ResponseState.Success(result)
            }
        }
        return ResponseState.Error(UniversalText.Dynamic(response.message()))
    }
}
