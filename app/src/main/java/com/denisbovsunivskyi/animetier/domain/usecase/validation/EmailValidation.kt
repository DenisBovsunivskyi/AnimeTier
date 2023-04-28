package com.denisbovsunivskyi.animetier.domain.usecase.validation

import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.domain.utils.MailMatcher
import com.denisbovsunivskyi.animetier.domain.utils.ValidationResult

class EmailValidation(
    private val matcher: MailMatcher
) {
    fun execute(email: String): ValidationResult {
        return when {
            blank(email) -> ValidationResult(
                false,
                UniversalText.Resource(id = R.string.error_email_cannot_be_blank)
            )
            notMatches(email) -> ValidationResult(
                false,
                UniversalText.Resource(id = R.string.error_email_pattern_not_match)
            )
            else -> ValidationResult(true)
        }
    }

    private fun blank(mail: String): Boolean {
        return mail.isBlank()
    }

    private fun notMatches(mail: String): Boolean {
        return !matcher.matches(mail)
    }
}