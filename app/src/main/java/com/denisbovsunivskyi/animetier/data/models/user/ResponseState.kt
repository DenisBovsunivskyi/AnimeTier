package com.denisbovsunivskyi.animetier.data.models.user

sealed class ResponseState <out T : Any,out U : Any> {
    data class Success <T: Any>(val data : T) : ResponseState<T, Nothing>()
    data class Error <U : Any>(val rawResponse: U) : ResponseState<Nothing, U>()
}