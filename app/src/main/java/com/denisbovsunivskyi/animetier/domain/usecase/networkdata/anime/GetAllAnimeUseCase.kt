package com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime

import com.denisbovsunivskyi.animetier.domain.repository.anime.AnimeRepository
import javax.inject.Inject

class GetAllAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
   suspend fun execute() = repository.fetchAllAnime()
}