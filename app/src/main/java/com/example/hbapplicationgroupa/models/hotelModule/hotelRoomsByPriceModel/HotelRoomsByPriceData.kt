package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsByPriceModel

import androidx.room.Entity

@Entity(tableName = "hotelRoom_by_price_table")
data class HotelRoomsByPriceData(
    val id: String,
    val isBooked: Boolean,
    val roomType: String
)
