package com.denisbovsunivskyi.animetier.domain.usecase.validation

import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.domain.common.ValidationResult

class ConfirmPasswordValidation {
    fun execute(password: String, confirmPassword: String): ValidationResult {
        return when {
            notMatch(password, confirmPassword) -> ValidationResult(
                false, UniversalText.Resource(id = R.string.error_confirm_password_not_match)
            )
            else -> ValidationResult(true)
        }
    }

    private fun notMatch(password: String, confirmPassword: String): Boolean {
         return password != confirmPassword
    }
}