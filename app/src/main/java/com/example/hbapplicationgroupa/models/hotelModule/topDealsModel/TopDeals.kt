package com.example.hbapplicationgroupa.models.hotelModule.topDealsModel

data class TopDeals(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<TopDealsData>,
    val Message: String,
    val errors: String?
)
