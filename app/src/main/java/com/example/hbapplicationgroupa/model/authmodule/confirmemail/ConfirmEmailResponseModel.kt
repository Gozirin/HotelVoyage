package com.example.hbapplicationgroupa.model.authmodule.confirmemail

import com.example.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class ConfirmEmailResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: UserIdModel,
    val Message: String,
    val errors: String?
)
