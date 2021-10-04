package com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel

data class UserHotel(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<UserHotelData>,
    val Message: String,
    val errors: String?
)
