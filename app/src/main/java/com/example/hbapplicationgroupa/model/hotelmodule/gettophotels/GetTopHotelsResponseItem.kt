package com.example.hbapplicationgroupa.model.hotelmodule.gettophotels

data class GetTopHotelsResponseItem(
    val id: String,
    var name: String,
    var description: String,
    val email: String,
    val address: String,
    val city: String,
    val state: String
)
