package com.denisbovsunivskyi.animetier.domain.repository.anime

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun fetchAnime(): Flow<ResponseState<AnimeData, UniversalText>>
}