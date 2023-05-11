package com.denisbovsunivskyi.animetier.presentation.model

import com.denisbovsunivskyi.animetier.presentation.model.anime.AnimeListUi

data class MainAnimeData(
    val id: Int,
    val type: DataItemType,
    var animeList: List<AnimeListUi>
    )
