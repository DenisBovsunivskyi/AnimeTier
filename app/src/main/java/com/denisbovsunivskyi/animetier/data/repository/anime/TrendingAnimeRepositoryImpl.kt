package com.denisbovsunivskyi.animetier.data.repository.anime

import com.denisbovsunivskyi.animetier.core.BaseRepository
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.models.anime.AnimeDataDto
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.anime.TrendingAnimeRepository
import kotlinx.coroutines.flow.Flow

class TrendingAnimeRepositoryImpl(
    private val animeDataSource: AnimeDataSource
) : BaseRepository(), TrendingAnimeRepository {
    override suspend fun fetchTrendingAnime(): Flow<ResponseState<AnimeDataDto, UniversalText>> {
        return doHandleRequest {
            animeDataSource.fetchTrendingAnime()
        }
    }
}



