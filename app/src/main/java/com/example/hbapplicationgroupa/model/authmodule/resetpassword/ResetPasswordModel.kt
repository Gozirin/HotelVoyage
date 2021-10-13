package com.example.hbapplicationgroupa.model.authmodule.resetpassword

data class ResetPasswordModel(
    val Token: String,
    val EmailAddress: String,
    val NewPassword: String,
    val ConfirmPassword: String
)
