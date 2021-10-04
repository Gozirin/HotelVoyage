package com.example.hbapplicationgroupa.models.hotelModule.hotelStatisticsModel

import androidx.room.Entity

@Entity(tableName = "hotel_statistics_table")
data class HotelStatisticsItem(
    val name: String,
    val numberOfRooms: Int,
    val averageRatings: String,
    val roomsOccupied: Int,
    val roomsUnOccupied: Int,
    val numberOfReviews: Int,
    val totalNumberOfBookings: Int,
    val totalEarnings: Int,
    val roomTypes: Int,
    val numberOfAmenities: Int
)
