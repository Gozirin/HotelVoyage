package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.hbapplicationgroupa.models.hotelModule.hotelModel.HotelData

@Dao
interface HotelDao {
    //Get all hotels
    @Query("SELECT * FROM hotel_table")
    fun getAllHotels(): LiveData<List<HotelData>>
}