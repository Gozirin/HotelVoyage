package com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

import androidx.room.Entity

@Entity(tableName = "bookingByUserId")
data class BookingByUserIdResponseItems(
    val BookingReference: String,
    val CheckIn: DateTime,
    val CheckOut: DateTime,
    val NumberOfPeople: Int,
    val ServiceName: String
)
