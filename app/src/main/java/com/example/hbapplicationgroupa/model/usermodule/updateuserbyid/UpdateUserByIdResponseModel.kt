package com.example.hbapplicationgroupa.model.usermodule.updateuserbyid

import com.example.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class UpdateUserByIdResponseModel(
    val statusCode: Int,
    val succeeded: Boolean,
    val data: String,
    val message: String,
    val errors: String?
)
