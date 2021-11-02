package com.example.hbapplicationgroupa.model.usermodule.getuserbyid

data class GetUserByIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: GetUserByIdResponseItem,
    val Message: String,
    val errors: String?
)
