package com.denisbovsunivskyi.animetier.data.repository.anime

import com.denisbovsunivskyi.animetier.core.BaseRepository
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.anime.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeRepositoryImpl(
    private val animeDataSource: AnimeDataSource
) : BaseRepository(), AnimeRepository {
    override suspend fun fetchAnime(): Flow<ResponseState<AnimeData, UniversalText>> {
        return doHandleRequest {
            animeDataSource.fetchAllAnime()
        }
    }
}



