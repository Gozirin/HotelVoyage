package com.example.hbapplicationgroupa.models.hotelModule.hotelByIdModel

data class HotelById(
    val statusCode: String,
    val success: Boolean,
    val data: HotelByIdData,
    val Message: String,
    val errors: String?
)

