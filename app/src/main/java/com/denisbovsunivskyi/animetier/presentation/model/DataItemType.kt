package com.denisbovsunivskyi.animetier.presentation.model

import com.denisbovsunivskyi.animetier.R

enum class DataItemType {
    HORIZONTAL_LIST,
    VERTICAL_LIST
}
enum class AnimeListType(val stringId:Int) {
    TRENDING(R.string.text_trending_anime),
    POPULAR_ONGOING(R.string.text_popular_ongoing),
    ALL(R.string.text_all)
}

