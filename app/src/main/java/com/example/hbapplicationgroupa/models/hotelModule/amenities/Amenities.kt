package com.example.hbapplicationgroupa.models.hotelModule.amenities

data class Amenities(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<AmenitiesData>,
    val Message: String,
    val errors: String?
)
