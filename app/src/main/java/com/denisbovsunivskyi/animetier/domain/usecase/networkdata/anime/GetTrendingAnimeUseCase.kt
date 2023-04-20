package com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.TrendingAnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.TrendingAnimeRepository
import javax.inject.Inject

class GetTrendingAnimeUseCase @Inject constructor(
    private val repository: TrendingAnimeRepository
) {
    suspend fun execute(): ResponseState<TrendingAnimeData, UniversalText> {
   return repository.fetchTrendingAnime()
    }
}