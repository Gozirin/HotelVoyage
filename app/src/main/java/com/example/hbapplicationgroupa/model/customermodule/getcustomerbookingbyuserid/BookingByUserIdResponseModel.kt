package com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponse

data class BookingByUserIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: ArrayList<BookingByUserIdResponseItems>,
    val Message: String,
    val errors: String?
)
