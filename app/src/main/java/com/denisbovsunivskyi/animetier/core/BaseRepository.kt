package com.denisbovsunivskyi.animetier.core

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    fun <T : Any> doHandleRequest(function: suspend () -> Response<T>): Flow<ResponseState<T, UniversalText>> {
        return flow {
            try {
                val response = function.invoke()
                response.let {
                    if (it.isSuccessful && it.body() != null) {
                        emit(ResponseState.Success(it.body()!!))
                    } else {
                        emit(ResponseState.Error(UniversalText.Dynamic("Something went wrong")))
                    }
                }
            } catch (ioException: IOException) {
                emit(ResponseState.Error(UniversalText.Dynamic("Poor network connection")))
            } catch (e: Exception) {
                emit(ResponseState.Error(UniversalText.Dynamic("Something went wrong")))
            }
        }
    }
}