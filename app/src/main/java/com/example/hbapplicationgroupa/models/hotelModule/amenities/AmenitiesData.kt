package com.example.hbapplicationgroupa.models.hotelModule.amenities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amenities_table")
data class AmenitiesData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val id: String,
    val name: String,
    val price: String,
    val discount: String

)
