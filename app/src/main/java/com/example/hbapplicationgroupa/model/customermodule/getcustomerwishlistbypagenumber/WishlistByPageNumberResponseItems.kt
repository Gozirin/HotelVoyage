package com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber

import androidx.room.Entity

@Entity(tableName = "wishlistByPageNumber")
data class WishlistByPageNumberResponseItems(
    val hotelId: String,
    val hotelName: String,
    val ImageUrl: String
)
