package com.example.hbapplicationgroupa.models.hotelModule.allHotelsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_hotels_table")
data class AllHotelsData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val id: String,
    val isBooked: Boolean,
    val roomType: String
)
