package com.example.hbapplicationgroupa.models

data class Users(
    val statusCode: String,
    val success: Boolean,
    val Data: ArrayList<UserItem>,
    val Message: String,
    val errors: String?
)