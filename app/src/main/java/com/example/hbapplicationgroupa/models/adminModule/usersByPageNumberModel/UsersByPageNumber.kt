package com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel

data class UsersByPageNumber(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<UsersByPageNumberData>,
    val Message: String,
    val errors: String?
)