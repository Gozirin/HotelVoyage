package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishListData

@Dao
interface WishListDao {
    //Get all hotels in the wishlist
    @Query("SELECT * FROM customer_wishlist_by_pagination_table")
    fun getCustomerWishlists(): LiveData<List<CustomerWishListData>>

    //Add hotel to wishlist
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWishlist(customerWishListData: CustomerWishListData)

    //Remove a wishlist
    @Delete
    fun removeWishlist(customerWishListData: CustomerWishListData)
}