package com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

data class BookingByUserIdResponseItems(
    val BookingReference: String,
    val CheckIn: DateTime,
    val CheckOut: DateTime,
    val NumberOfPeople: Int,
    val ServiceName: String
)
