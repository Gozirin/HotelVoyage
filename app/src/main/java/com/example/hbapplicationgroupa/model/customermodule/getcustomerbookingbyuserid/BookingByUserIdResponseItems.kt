package com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookingByUserId")
data class BookingByUserIdResponseItems(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val BookingReference: String,
    val CheckIn: DateTime,
    val CheckOut: DateTime,
    val NumberOfPeople: Int,
    val ServiceName: String
)
