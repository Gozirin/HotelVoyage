package com.example.hbapplicationgroupa.model.authmodule.forgotpassword

import com.example.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class ForgotPasswordResponseModel(
    val Data: String,
    val success: Boolean,
    val Message: String,
    val statusCode: Int )
