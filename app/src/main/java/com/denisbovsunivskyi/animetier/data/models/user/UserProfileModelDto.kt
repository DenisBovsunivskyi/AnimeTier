package com.denisbovsunivskyi.animetier.data.models.user

import java.io.File

data class UserProfileModelDto(
    var userId: String?=null,
    val nickName: String,
    val email: String,
    val about:String,
    val photo:File?
)