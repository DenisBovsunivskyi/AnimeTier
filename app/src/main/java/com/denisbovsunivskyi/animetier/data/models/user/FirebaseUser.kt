package com.denisbovsunivskyi.animetier.data.models.user

import java.io.File

data class FirebaseUser(
    var userId: String?=null,
    val nickName: String,
    val fullName: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val photo:File?
)