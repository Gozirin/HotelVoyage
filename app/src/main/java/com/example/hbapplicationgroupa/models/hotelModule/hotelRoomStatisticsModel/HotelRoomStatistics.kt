package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomStatisticsModel

data class HotelRoomStatistics(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<HotelRoomStatisticsData>,
    val Message: String,
    val errors: String?
)
