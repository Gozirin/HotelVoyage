package com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel

import androidx.room.Entity

@Entity(tableName = "hotel_ratings_table")
data class HotelRatingsData(
    val id: String,
    val rating: Int,
    val customerIs: String
)
