package com.example.hbapplicationgroupa.model.authmodule.resetpassword

data class ResetPasswordModel(
    val EmailAddress: String,
    val Token: String,
    val Password: String
)
