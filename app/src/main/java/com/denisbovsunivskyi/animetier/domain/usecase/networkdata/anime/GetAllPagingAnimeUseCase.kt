package com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime

import com.denisbovsunivskyi.animetier.domain.repository.anime.AnimeRepository
import javax.inject.Inject

class GetAllPagingAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
   suspend fun execute(string: String?) = repository.fetchPagingAnime(search = string,null,null)
}