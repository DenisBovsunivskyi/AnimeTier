package com.denisbovsunivskyi.animetier.core.utils.validation

import android.util.Patterns
import com.denisbovsunivskyi.animetier.domain.common.MailMatcher


class AndroidMailMatcher : MailMatcher {

    override fun matches(mail: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}