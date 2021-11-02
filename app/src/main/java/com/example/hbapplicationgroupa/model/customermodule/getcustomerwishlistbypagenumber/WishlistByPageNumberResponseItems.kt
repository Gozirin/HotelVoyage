package com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlistByPageNumber")
data class WishlistByPageNumberResponseItems(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val hotelId: String,
    val hotelName: String,
    val percentageRating: String,
    val price: Double,
    val description: String,
    val ImageUrl: String
)
