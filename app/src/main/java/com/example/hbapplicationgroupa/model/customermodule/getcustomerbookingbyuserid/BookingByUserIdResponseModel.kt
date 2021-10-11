package com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

data class BookingByUserIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: ArrayList<BookingByUserIdResponseItems>,
    val Message: String,
    val errors: String?
)
