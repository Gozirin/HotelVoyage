package com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel

import androidx.room.Entity

@Entity(tableName = "user_hotels_table")
data class UserHotelData(
    val hotelId: String,
    val hotelName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val city: String,
    val state: String
)
