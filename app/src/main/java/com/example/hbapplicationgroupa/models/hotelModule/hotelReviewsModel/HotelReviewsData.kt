package com.example.hbapplicationgroupa.models.hotelModule.hotelReviewsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotel_reviews_table")
data class HotelReviewsData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val review: String,
    val reviewText: String,
    val customerId: String,
    val createdAt: String,
    val updatedAt: String
)
