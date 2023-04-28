package com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.anime.AnimeDataDto
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.anime.TrendingAnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingAnimeUseCase @Inject constructor(
    private val repository: TrendingAnimeRepository
) {
    suspend fun execute(): Flow<ResponseState<AnimeDataDto, UniversalText>> {
         return repository.fetchTrendingAnime()
    }
}