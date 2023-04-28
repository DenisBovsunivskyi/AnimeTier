package com.denisbovsunivskyi.animetier.domain.utils

interface MailMatcher {
    fun matches(mail: String): Boolean
}