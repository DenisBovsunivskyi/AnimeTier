package com.denisbovsunivskyi.animetier.presentation.model

import com.denisbovsunivskyi.animetier.presentation.model.anime.AnimeListUi

data class MainAnimeData(
    val id: Int,
    val type: DataItemType,
    val animeType: AnimeListType,
    var animeList: List<AnimeListUi>
    )
