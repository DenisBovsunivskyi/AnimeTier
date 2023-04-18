package com.denisbovsunivskyi.animetier.data.models.user

import android.net.Uri

data class UserProfileModelDto(
    var userId: String,
    val nickName: String,
    val email: String,
    val about: String,
    val photo: Uri?
)