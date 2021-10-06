package com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_hotels_table")
data class UserHotelData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val hotelId: String,
    val hotelName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val city: String,
    val state: String
)
