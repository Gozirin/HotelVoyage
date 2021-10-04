package com.example.hbapplicationgroupa.models.adminModule.userByIdModel

import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumberData

data class UserById(
    val statusCode: String,
    val success: Boolean,
    val data: UserByIdData,
    val Message: String,
    val errors: String?
)
