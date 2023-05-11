package com.denisbovsunivskyi.animetier.core.network

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.DataLayerMapper
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T : Any> doHandleRequest(function: suspend () -> Response<T>): Flow<ResponseState<T, UniversalText>> {
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

    protected fun <T : DataLayerMapper<S>, S : Any> doNetworkRequestWithMapping(
        request: suspend () -> Response<T>
    ) = flow<ResponseState<S, UniversalText>> {
        request().let {
            if (it.isSuccessful && it.body() != null) {
                emit(ResponseState.Success(it.body()!!.toDomain()))
            } else {
                emit(ResponseState.Error(UniversalText.Dynamic("Something went wrong")))
            }
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(
            ResponseState.Error(
                UniversalText.Dynamic(
                    exception.localizedMessage ?: "Error Occurred!"
                )
            )
        )
    }

}
