package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsByPriceModel

data class HotelRoomsByPrice(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<HotelRoomsByPriceData>,
    val Message: String,
    val errors: String?
)
