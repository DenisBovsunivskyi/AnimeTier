package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeData
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.utils.ResponseResult
import com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime.GetAllAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAnimeViewModel @Inject constructor(
    private val allAnimeUseCase: GetAllAnimeUseCase
) :
    ViewModel() {
    val allAnimeList: MutableLiveData<ResponseResult<AnimeData>> =
        MutableLiveData()

    init {
        getAlLAnime()
    }

     fun getAlLAnime() {
        viewModelScope.launch {
            allAnimeList.postValue(ResponseResult.Loading())
            allAnimeUseCase.execute().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        allAnimeList.postValue(ResponseResult.Success(response.data))
                    }

                    is ResponseState.Error -> {
                        allAnimeList.postValue(ResponseResult.Error(response.rawResponse))
                    }
                }
            }

        }
    }

}