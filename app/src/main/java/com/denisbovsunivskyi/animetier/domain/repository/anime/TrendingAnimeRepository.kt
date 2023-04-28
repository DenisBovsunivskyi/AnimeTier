package com.denisbovsunivskyi.animetier.domain.repository.anime

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.anime.AnimeDataDto
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import kotlinx.coroutines.flow.Flow

interface TrendingAnimeRepository {
    suspend fun fetchTrendingAnime(): Flow<ResponseState<AnimeDataDto, UniversalText>>
}