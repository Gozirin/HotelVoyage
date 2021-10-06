package com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotel_amenities_table")
data class HotelAmenitiesData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val id: String,
    val name: String,
    val price: String,
    val discount: String
)
