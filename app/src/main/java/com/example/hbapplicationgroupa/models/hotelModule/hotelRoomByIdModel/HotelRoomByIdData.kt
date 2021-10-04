package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel

import androidx.room.Entity

@Entity(tableName = "hotelRoom_by_id_table")
data class HotelRoomByIdData(
    val id: String,
    val roomNo: Int,
    val isBooked: Boolean,
    val roomTypeId: String
)
