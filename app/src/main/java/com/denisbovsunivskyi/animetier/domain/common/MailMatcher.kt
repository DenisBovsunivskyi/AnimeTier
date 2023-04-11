package com.denisbovsunivskyi.animetier.domain.common

interface MailMatcher {
    fun matches(mail: String): Boolean
}