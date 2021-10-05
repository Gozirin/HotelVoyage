package com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel

import androidx.room.Entity
import androidx.room.PrimaryKey

//table for customer wishlist in pagination gotten by user id
@Entity(tableName = "customer_wishlist_by_pagination_table")
data class CustomerWishListData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    var hotelId: String,
    var hotelName: String,
    var imageUrl: Int
)
