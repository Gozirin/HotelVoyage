package com.example.hbapplicationgroupa.models.hotelModule.topHotelsModel

import androidx.room.Entity

@Entity(tableName = "top_hotels_table")
data class TopHotelsData(
    val id: String,
    var name: String,
    var description: String,
    val email: String,
    val address: String,
    val city: String,
    val state: String
)
