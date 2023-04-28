package com.denisbovsunivskyi.animetier.data.models

interface DataLayerMapper<out T> {
    fun toDomain(): T
}