package com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals

data class GetTopDealsResponseItem(
    val hotelId: String,
    val name: String,
    val description: String,
    val price: Double,
    val discount: Double,
    val thumbnail: String,
    val hotelName: String
)
