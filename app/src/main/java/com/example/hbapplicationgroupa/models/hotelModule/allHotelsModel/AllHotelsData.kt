package com.example.hbapplicationgroupa.models.hotelModule.allHotelsModel

import androidx.room.Entity

@Entity(tableName = "all_hotels_table")
data class AllHotelsData(
    val id: String,
    val isBooked: Boolean,
    val roomType: String
)
