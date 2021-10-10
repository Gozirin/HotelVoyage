package com.example.hbapplicationgroupa.model.authmodule.adduser

data class AddUserResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: UserIdModel,
    val Message: String,
    val errors: String?
)
