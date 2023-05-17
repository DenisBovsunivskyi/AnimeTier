package com.denisbovsunivskyi.animetier.domain.repository.anime

import androidx.paging.PagingData
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeData
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeModel
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend  fun fetchPagingAnime(sortType:String?,status:String?): Flow<PagingData<AnimeModel>>
    suspend fun fetchAllAnime(sortType:String?,status:String?): Flow<ResponseState<AnimeData, UniversalText>>
}