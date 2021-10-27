package com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

data class BookingByUserIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<BookingByUserIdResponseItems>,
    val message: String,
    val errors: String?
)
