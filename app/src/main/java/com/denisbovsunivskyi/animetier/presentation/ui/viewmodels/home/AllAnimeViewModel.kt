package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeData
import com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime.GetAllAnimeUseCase
import com.denisbovsunivskyi.animetier.domain.utils.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAnimeViewModel @Inject constructor(
    private val allAnimeUseCase: GetAllAnimeUseCase
) : ViewModel() {
   private val allAnimeList: MutableLiveData<ResponseResult<AnimeData>> =
        MutableLiveData()
    fun getAllAnimeList():LiveData<ResponseResult<AnimeData>> = allAnimeList

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