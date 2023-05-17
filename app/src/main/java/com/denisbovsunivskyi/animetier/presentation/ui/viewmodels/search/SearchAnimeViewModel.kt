package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime.GetAllPagingAnimeUseCase
import com.denisbovsunivskyi.animetier.presentation.model.anime.AnimeListUi
import com.denisbovsunivskyi.animetier.presentation.model.anime.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchAnimeViewModel @Inject constructor(
    private val pagingAnimeUseCase: GetAllPagingAnimeUseCase,
) : ViewModel() {


    suspend fun getAnimeList(): Flow<PagingData<AnimeListUi>> {
        return pagingAnimeUseCase.execute().map { pagingData ->
            pagingData.map { it.toUI() }
        }.cachedIn(viewModelScope)
    }

}

sealed class AllAnimeActions {
    object Loading : AllAnimeActions()
    object Success : AllAnimeActions()
    sealed class Failed : AllAnimeActions() {
        data class LoadingFailed(val message: UniversalText) : AllAnimeActions()
    }
}
