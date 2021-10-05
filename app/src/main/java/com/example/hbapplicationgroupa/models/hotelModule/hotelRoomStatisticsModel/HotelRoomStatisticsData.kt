package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomStatisticsModel

import androidx.room.Entity

@Entity(tableName = "hotel_room_statistics_table")
data class HotelRoomStatisticsData(
    val roomType: String,
    val numberOfRooms: Int,
    val numberOfBookedRooms: Int,
    val price: Int
)
