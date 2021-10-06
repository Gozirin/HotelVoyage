package com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotel_ratings_table")
data class HotelRatingsData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val id: String,
    val rating: Int,
    val customerIs: String
)
