package com.denisbovsunivskyi.animetier.presentation.model.user

data class UserInfo(
    var userId: String,
    val nickName:String,
    val email:String,
    val about:String,
    val picture:String
  )