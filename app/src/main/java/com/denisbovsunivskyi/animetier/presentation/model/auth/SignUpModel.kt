package com.denisbovsunivskyi.animetier.presentation.model.auth

import androidx.databinding.ObservableField

data class SignUpModel(
    val email: ObservableField<String> = ObservableField(""),
    val password: ObservableField<String> = ObservableField(""),
    val confirmPassword: ObservableField<String> = ObservableField("")
)