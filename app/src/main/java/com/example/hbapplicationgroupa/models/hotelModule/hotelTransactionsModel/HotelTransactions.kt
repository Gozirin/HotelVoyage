package com.example.hbapplicationgroupa.models.hotelModule.hotelTransactionsModel

data class HotelTransactions(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<HotelTransactionsData>,
    val Message: String,
    val errors: String?
)
