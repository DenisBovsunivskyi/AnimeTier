package com.denisbovsunivskyi.animetier.presentation.model.auth

import androidx.databinding.ObservableField

class SignInModel(
    val email: ObservableField<String> = ObservableField(""),
    val password: ObservableField<String> = ObservableField("")
    )