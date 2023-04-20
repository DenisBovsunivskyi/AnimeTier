package com.denisbovsunivskyi.animetier.domain.repository

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.TrendingAnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState

interface TrendingAnimeRepository {
    suspend fun fetchTrendingAnime(): ResponseState<TrendingAnimeData, UniversalText>
}