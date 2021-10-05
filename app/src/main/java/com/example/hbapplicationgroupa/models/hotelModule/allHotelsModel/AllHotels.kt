package com.example.hbapplicationgroupa.models.hotelModule.allHotelsModel

data class AllHotels(
    val statusCode: String,
    val success: Boolean,
    val Data: ArrayList<AllHotelsData>,
    val Message: String,
    val errors: String?
)
