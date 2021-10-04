package com.example.hbapplicationgroupa.models.hotelModule.hotelModel

data class Hotel(
    val statusCode: String,
    val success: Boolean,
    val data: HotelData,
    val Message: String,
    val errors: String?
)
