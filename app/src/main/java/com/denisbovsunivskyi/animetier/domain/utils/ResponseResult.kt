package com.denisbovsunivskyi.animetier.domain.utils

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import java.io.Serializable

sealed class ResponseResult<T>(
    val data: T? = null,
    val message: UniversalText? = null
): Serializable {
    class Success<T>(data: T) : ResponseResult<T>(data)
    class Loading<T>(data: T? = null) : ResponseResult<T>(data)
    class Error<T>(message: UniversalText, data: T? = null) : ResponseResult<T>(data, message)
}