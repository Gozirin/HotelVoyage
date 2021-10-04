package com.example.hbapplicationgroupa.models.hotelModule.hotelManagerStatisticsModel

data class HotelManagerStatistics(
    val statusCode: String,
    val success: Boolean,
    val data: HotelManagerStatisticsData,
    val Message: String,
    val errors: String?
)
