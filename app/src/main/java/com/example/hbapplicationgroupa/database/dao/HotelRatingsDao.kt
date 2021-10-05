package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatingsData

@Dao
interface HotelRatingsDao {
    //Get ratings by hotel id
    @Query("SELECT * FROM hotel_ratings_table WHERE id LIKE :hotelId")
    fun getRatingsByHotelId(hotelId: String): LiveData<List<HotelRatingsData>>

    //Add a rating
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHotelRating(hotelRatingsData: HotelRatingsData)
}