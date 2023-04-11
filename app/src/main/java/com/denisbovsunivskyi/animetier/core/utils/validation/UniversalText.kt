package com.denisbovsunivskyi.animetier.core.utils.validation

import android.content.Context
import androidx.annotation.StringRes

sealed class UniversalText {

    companion object {
        private const val EMPTY = ""
    }

    data class Dynamic(val value: String) : UniversalText()
    class Resource(@StringRes val id: Int, vararg val args: Any) : UniversalText()
    object Empty : UniversalText()



    fun asString(context: Context): String {
        return when (this) {
            is Dynamic -> value
            is Resource -> context.getString(id, *args)
            Empty -> EMPTY
        }
    }
}