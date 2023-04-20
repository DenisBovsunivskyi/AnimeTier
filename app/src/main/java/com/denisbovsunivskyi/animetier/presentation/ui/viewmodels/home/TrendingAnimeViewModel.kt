package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.TrendingAnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.common.ResponseResult
import com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime.GetTrendingAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeViewModel @Inject constructor(
    private val trendingAnimeUseCase: GetTrendingAnimeUseCase
) :
    ViewModel() {
    val trendingAnimeList: MutableLiveData<ResponseResult<TrendingAnimeData>> =
        MutableLiveData()

    init {
        getTrendingAnime()
    }

    private fun getTrendingAnime() {
        viewModelScope.launch {
            trendingAnimeList.postValue(ResponseResult.Loading())
            val response = trendingAnimeUseCase.execute()
            when (response) {
                is ResponseState.Success -> {
                    trendingAnimeList.postValue(ResponseResult.Success(response.data))
                }

                is ResponseState.Error -> {
                    trendingAnimeList.postValue(ResponseResult.Error(response.rawResponse))
                }
            }
        }
    }

}