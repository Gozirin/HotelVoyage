package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems

@Dao
interface WishlistByPageNumberDao {
    @Query("SELECT * FROM wishlistByPageNumber")
    fun getWishlistByPageNumber(): LiveData<List<WishlistByPageNumberResponseItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWishlist(wishlist: WishlistByPageNumberResponseItems)

    @Delete
    fun removeWishlist(wishlist: WishlistByPageNumberResponseItems)
}