package com.example.hbapplicationgroupa.model.authmodule.loginuser

import com.google.gson.annotations.SerializedName

data class LoginUserResponseModel(
    val data: LoginUserResponse?,
    val succeeded: Boolean,
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int
)
