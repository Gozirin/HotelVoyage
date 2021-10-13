package com.example.hbapplicationgroupa.model.authmodule.loginuser

data class LoginUserResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: LoginUserResponse,
    val Message: String,
    val errors: String?
)
