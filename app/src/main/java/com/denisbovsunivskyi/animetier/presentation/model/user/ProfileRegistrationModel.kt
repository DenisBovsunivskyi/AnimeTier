package com.denisbovsunivskyi.animetier.presentation.model.user

import android.net.Uri
import androidx.databinding.ObservableField

data class ProfileRegistrationModel(
    val nickname: ObservableField<String> = ObservableField(""),
    val about: ObservableField<String> = ObservableField(""),
    var photo: Uri? = null
)
