package com.example.hbapplicationgroupa.models.hotelModule.amenities

import androidx.room.Entity

@Entity(tableName = "amenities_table")
data class AmenitiesData(
    val id: String,
    val name: String,
    val price: String,
    val discount: String

)
