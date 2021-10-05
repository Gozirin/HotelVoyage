package com.example.hbapplicationgroupa.models.hotelModule.hotelReviewsModel

import androidx.room.PrimaryKey

data class HotelReviewsData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val review: String,
    val reviewText: String,
    val customerId: String,
    val createdAt: String,
    val updatedAt: String
)
