package com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel

data class HotelRatings(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<HotelRatingsData>,
    val Message: String,
    val errors: String?
)
