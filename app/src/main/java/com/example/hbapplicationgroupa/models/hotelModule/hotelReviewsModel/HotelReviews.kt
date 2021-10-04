package com.example.hbapplicationgroupa.models.hotelModule.hotelReviewsModel

import androidx.room.Entity

@Entity(tableName = "hotel_reviews_table")
data class HotelReviews(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<HotelReviewsData>,
    val Message: String,
    val errors: String?
)
