package com.denisbovsunivskyi.animetier.core

class Event<T>(content: T?) {
    private var content: T
    private var hasBeenHandled = false

    init {
        requireNotNull(content) { "null values in Event are not allowed." }
        this.content = content
    }

    fun hasBeenHandled(): Boolean {
        return hasBeenHandled
    }

    val contentIfNotHandled: T?
        get() = if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }

    fun setContent(content: T) {
        this.content = content
        hasBeenHandled = false
    }
}