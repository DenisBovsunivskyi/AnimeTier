package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeData
import com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime.GetTrendingAnimeUseCase
import com.denisbovsunivskyi.animetier.domain.utils.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeViewModel @Inject constructor(
    private val trendingAnimeUseCase: GetTrendingAnimeUseCase
) :
    ViewModel() {
    private val trendingAnimeList: MutableLiveData<ResponseResult<AnimeData>> =
        MutableLiveData()

    fun getTrendingAnimeList(): LiveData<ResponseResult<AnimeData>> = trendingAnimeList

    init {
        getTrendingAnime()
    }

    fun getTrendingAnime() {
        viewModelScope.launch {
            trendingAnimeList.postValue(ResponseResult.Loading())
            trendingAnimeUseCase.execute().collect { response ->
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

}