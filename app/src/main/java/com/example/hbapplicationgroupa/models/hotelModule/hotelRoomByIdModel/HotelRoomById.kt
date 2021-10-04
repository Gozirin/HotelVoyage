package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel

data class HotelRoomById(
    val statusCode: String,
    val success: Boolean,
    val data: HotelRoomByIdData,
    val Message: String,
    val errors: String?
)
