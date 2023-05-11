package com.denisbovsunivskyi.animetier.data.repository.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.denisbovsunivskyi.animetier.core.network.BaseRepository
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeAllPagingSource
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeData
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeModel
import com.denisbovsunivskyi.animetier.domain.repository.anime.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeRepositoryImpl(
    private val animeAllPagingSource: AnimeAllPagingSource,
    private val animeDataSource: AnimeDataSource
) : BaseRepository(), AnimeRepository {
    override suspend fun fetchPagingAnime(): Flow<PagingData<AnimeModel>> {
        return Pager(
            config = PagingConfig(pageSize = 15),
            pagingSourceFactory = { animeAllPagingSource }
        ).flow
    }

    override suspend fun fetchAllAnime(): Flow<ResponseState<AnimeData, UniversalText>> {
        return doNetworkRequestWithMapping {
            animeDataSource.fetchAllAnime()
        }
    }
}



