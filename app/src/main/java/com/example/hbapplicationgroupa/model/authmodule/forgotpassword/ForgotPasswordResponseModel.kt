package com.example.hbapplicationgroupa.model.authmodule.forgotpassword

import com.example.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class ForgotPasswordResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: UserIdModel,
    val Message: String,
    val errors: String?
)
