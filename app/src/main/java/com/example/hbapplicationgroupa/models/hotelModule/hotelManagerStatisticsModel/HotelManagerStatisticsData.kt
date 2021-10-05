package com.example.hbapplicationgroupa.models.hotelModule.hotelManagerStatisticsModel

import androidx.room.Entity

@Entity(tableName = "hotel_manager_statistics_table")
data class HotelManagerStatisticsData(

    val numberOfHotels: Int,
    val averageHotelRatings: Double,
    val totalNumberOfCustomers: Int

)
