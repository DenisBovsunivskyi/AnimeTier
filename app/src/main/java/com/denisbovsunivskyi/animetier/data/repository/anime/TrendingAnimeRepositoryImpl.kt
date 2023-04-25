package com.denisbovsunivskyi.animetier.data.repository.anime

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.TrendingAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class TrendingAnimeRepositoryImpl(
    private val animeDataSource: AnimeDataSource
) : TrendingAnimeRepository {
    override suspend fun fetchTrendingAnime(): Flow<ResponseState<AnimeData, UniversalText>> {
        return doHandleRequest {
            animeDataSource.fetchTrendingAnime()
        }
    }
}

fun <T : Any> doHandleRequest(function: suspend () -> Response<T>): Flow<ResponseState<T, UniversalText>> {
    return flow {
        val response = function.invoke()
        response.let {
            if (it.isSuccessful && it.body() != null) {
                emit(ResponseState.Success(it.body()!!))
            } else {
                emit(ResponseState.Error(UniversalText.Dynamic("Something went wrong")))
            }
        }
    }
}


