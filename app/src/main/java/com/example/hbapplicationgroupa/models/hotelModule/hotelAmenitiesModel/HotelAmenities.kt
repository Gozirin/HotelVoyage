package com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel

data class HotelAmenities(
    val statusCode: String,
    val success: Boolean,
    val data: HotelAmenitiesData,
    val Message: String,
    val errors: String?
)
