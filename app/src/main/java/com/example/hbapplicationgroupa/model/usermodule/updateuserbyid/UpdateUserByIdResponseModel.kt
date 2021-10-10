package com.example.hbapplicationgroupa.model.usermodule.updateuserbyid

import com.example.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class UpdateUserByIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: UserIdModel,
    val Message: String,
    val errors: String?
)
