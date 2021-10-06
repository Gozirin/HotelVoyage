package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishListData

interface WishListDaoRepositoryInterface {

    //Get all hotels in the wishlist
    fun getCustomerWishlists(): LiveData<List<CustomerWishListData>>

    //Add hotel to wishlist
    fun insertWishlist(customerWishListData: CustomerWishListData)

    //Remove a wishlist
    fun removeWishlist(customerWishListData: CustomerWishListData)
}