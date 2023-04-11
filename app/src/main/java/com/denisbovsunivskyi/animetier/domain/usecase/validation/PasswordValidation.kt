package com.denisbovsunivskyi.animetier.domain.usecase.validation

import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.domain.common.ValidationResult

class PasswordValidation {
    fun execute(password: String): ValidationResult {
        return when {
            tooShort(password) -> ValidationResult(
                false,
                UniversalText.Resource(
                    id = R.string.error_password_too_short,
                    args = arrayOf(PASSWORD_LENGTH)
                )
            )
            else -> ValidationResult(true)
        }
    }

    private fun tooShort(password: String): Boolean {
        return if (password.length >= PASSWORD_LENGTH) {
            return false
        } else true
    }

    companion object {
        const val PASSWORD_LENGTH = 8
    }
}