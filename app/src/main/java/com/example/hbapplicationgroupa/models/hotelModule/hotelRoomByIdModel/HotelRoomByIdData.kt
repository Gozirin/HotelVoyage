package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotelRoom_by_id_table")
data class HotelRoomByIdData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val id: String,
    val roomNo: Int,
    val isBooked: Boolean,
    val roomTypeId: String
)
