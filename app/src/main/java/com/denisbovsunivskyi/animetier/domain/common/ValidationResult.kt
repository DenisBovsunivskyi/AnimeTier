package com.denisbovsunivskyi.animetier.domain.common

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText

data class ValidationResult(
    val successful:Boolean,
    val errorMessage: UniversalText? = null
)
