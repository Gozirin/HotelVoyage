package com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel

import androidx.room.Entity
//table for customer wishlist in pagination gotten by user id
@Entity(tableName = "customer_wishlist_by_pagination_table")
data class CustomerWishListData(
    var hotelId: String,
    var hotelName: String,
    var imageUrl: Int
)
