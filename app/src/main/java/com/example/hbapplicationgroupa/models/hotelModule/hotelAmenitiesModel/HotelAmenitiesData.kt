package com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel

import androidx.room.Entity

@Entity(tableName = "hotel_amenities_table")
data class HotelAmenitiesData(
    val id: String,
    val name: String,
    val price: String,
    val discount: String
)
