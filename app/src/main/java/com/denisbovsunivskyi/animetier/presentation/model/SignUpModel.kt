package com.denisbovsunivskyi.animetier.presentation.model

import androidx.databinding.ObservableField
import java.io.File

data class SignUpModel(
    val email:ObservableField<String> = ObservableField(""),
    val password:ObservableField<String> = ObservableField(""),
    val confirmPassword:ObservableField<String> = ObservableField(""),
    val userName:ObservableField<String> = ObservableField(""),
    val nickName:ObservableField<String> = ObservableField(""),
    val photo:ObservableField<File> = ObservableField(),
)