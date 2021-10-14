package com.example.hbapplicationgroupa.model.authmodule.forgotpassword

import com.example.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class ForgotPasswordResponseModel(
    val data: String?,
    val succeeded: Boolean,
    val message: String?,
    val statusCode: Int? )
