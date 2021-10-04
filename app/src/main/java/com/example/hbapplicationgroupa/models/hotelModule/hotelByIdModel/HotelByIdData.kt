package com.example.hbapplicationgroupa.models.hotelModule.hotelByIdModel

import androidx.room.Entity

@Entity(tableName = "hotel_by_id_table")
data class HotelByIdData(
    val id: String,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val rating: Double,
    val gallery: ArrayList<Int>,
    val reviews: ArrayList<HotelByIdReviews>,
    val roomTypes: ArrayList<HotelByIdRoomType>

)
