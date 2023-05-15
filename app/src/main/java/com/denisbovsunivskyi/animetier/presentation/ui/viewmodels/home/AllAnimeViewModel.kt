package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.core.Event
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime.GetAllAnimeUseCase
import com.denisbovsunivskyi.animetier.domain.usecase.networkdata.anime.GetTrendingAnimeUseCase
import com.denisbovsunivskyi.animetier.presentation.model.AnimeListType
import com.denisbovsunivskyi.animetier.presentation.model.DataItemType
import com.denisbovsunivskyi.animetier.presentation.model.MainAnimeData
import com.denisbovsunivskyi.animetier.presentation.model.anime.AnimeListUi
import com.denisbovsunivskyi.animetier.presentation.model.anime.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAnimeViewModel @Inject constructor(
    private val allAnimeUseCase: GetAllAnimeUseCase,
    private val trendingAnimeUseCase: GetTrendingAnimeUseCase
) : ViewModel() {
    private val mainAnimeData: MutableLiveData<List<MainAnimeData>> =
        MutableLiveData(
            listOf(
                MainAnimeData(1, DataItemType.HORIZONTAL_LIST, AnimeListType.TRENDING, emptyList()),
                MainAnimeData(
                    2,
                    DataItemType.HORIZONTAL_LIST,
                    AnimeListType.POPULAR_ONGOING,
                    emptyList()
                ),
                MainAnimeData(3, DataItemType.VERTICAL_LIST, AnimeListType.ALL, emptyList()),
            )
        )
    private val mAllAnimeEventLiveData: MutableLiveData<Event<AllAnimeActions>> =
        MutableLiveData<Event<AllAnimeActions>>()

    fun getEventLiveData(): LiveData<Event<AllAnimeActions>> {
        return mAllAnimeEventLiveData
    }

    fun getMainAnimeLiveData(): LiveData<List<MainAnimeData>> = mainAnimeData

    init {
        fetchAllData()
    }

    fun fetchAllData() {
        getTrendingAnimeData()
        getAllAnimeData()
        getPopularCurrentAnimeData()
    }

    fun getTrendingAnimeData() {
        viewModelScope.launch {
            mAllAnimeEventLiveData.postValue(Event(AllAnimeActions.Loading))
            trendingAnimeUseCase.execute().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        mAllAnimeEventLiveData.postValue(Event(AllAnimeActions.Success))
                        postNewListToLiveData(
                            response.data.data.map { it.toUI() },
                            AnimeListType.TRENDING
                        )
                    }

                    is ResponseState.Error -> {
                        mAllAnimeEventLiveData.postValue(
                            Event(
                                AllAnimeActions.Failed.LoadingFailed(
                                    response.rawResponse
                                )
                            )
                        )

                    }
                }
            }

        }
    }

    fun getPopularCurrentAnimeData() {
        viewModelScope.launch {
            allAnimeUseCase.execute(sortType = "-user_count", status = "current")
                .collect { response ->
                    when (response) {
                        is ResponseState.Success -> {
                            mAllAnimeEventLiveData.postValue(Event(AllAnimeActions.Success))
                            postNewListToLiveData(
                                response.data.data.map { it.toUI() },
                                AnimeListType.POPULAR_ONGOING
                            )
                        }

                        is ResponseState.Error -> {
                            mAllAnimeEventLiveData.postValue(
                                Event(
                                    AllAnimeActions.Failed.LoadingFailed(
                                        response.rawResponse
                                    )
                                )
                            )
                        }
                    }
                }

        }
    }

    fun getAllAnimeData() {
        viewModelScope.launch {
            allAnimeUseCase.execute().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        mAllAnimeEventLiveData.postValue(Event(AllAnimeActions.Success))
                        postNewListToLiveData(
                            response.data.data.map { it.toUI() },
                            AnimeListType.ALL
                        )
                    }

                    is ResponseState.Error -> {
                        mAllAnimeEventLiveData.postValue(
                            Event(
                                AllAnimeActions.Failed.LoadingFailed(
                                    response.rawResponse
                                )
                            )
                        )
                    }
                }
            }

        }
    }

    private fun postNewListToLiveData(list: List<AnimeListUi>, animeListType: AnimeListType) {
        val copyList = mainAnimeData.value?.toMutableList() ?: mutableListOf()
        copyList.find { it.animeType == animeListType }?.animeList = list
        mainAnimeData.postValue(copyList)
    }
}

sealed class AllAnimeActions {
    object Loading : AllAnimeActions()
    object Success : AllAnimeActions()
    sealed class Failed : AllAnimeActions() {
        data class LoadingFailed(val message: UniversalText) : AllAnimeActions()
    }
}
