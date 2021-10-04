package com.example.hbapplicationgroupa.models.hotelModule.topHotelsModel

data class TopHotels(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<TopHotelsData>,
    val Message: String,
    val errors: String?
)