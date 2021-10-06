package com.example.hbapplicationgroupa.models.hotelModule.hotelModel

import androidx.room.Entity
import androidx.room.PrimaryKey

//table for an hotel gotten by id
@Entity(tableName = "hotel_table")
data class HotelData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    var name: String,
    var description: String,
    val email: String,
    val address: String,
    val state: String
)
