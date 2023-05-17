package com.denisbovsunivskyi.animetier.data.datasource.anime

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.denisbovsunivskyi.animetier.data.api.AnimeServiceApi
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeModel

class AnimeAllPagingSource(
    private val api: AnimeServiceApi
) : PagingSource<Int, AnimeModel>() {


    override fun getRefreshKey(state: PagingState<Int, AnimeModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeModel> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = api.getAllAnime(offset = nextPageNumber)
            val body = response.body()?.toDomain()

            val nextKey =
                if (response.body()?.data?.isEmpty() == true) {
                    null
                } else {
                    nextPageNumber + 15
                }
            LoadResult.Page(
                data = body?.data?: emptyList(),
                prevKey = if (nextPageNumber == 0) null else nextPageNumber - 1,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
