package com.example.hbapplicationgroupa.models.hotelModule.hotelStatisticsModel

data class HotelStatistics(
    val statusCode: String,
    val success: Boolean,
    val Data: HotelStatisticsItem,
    val Message: String,
    val errors: String?
)
