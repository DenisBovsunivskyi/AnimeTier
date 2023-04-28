package com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeData
import com.denisbovsunivskyi.animetier.domain.repository.anime.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend fun execute(): Flow<ResponseState<AnimeData, UniversalText>> {
        return repository.fetchAnime()
    }
}