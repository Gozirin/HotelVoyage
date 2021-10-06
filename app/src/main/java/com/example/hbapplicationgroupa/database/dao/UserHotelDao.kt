package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel.UserHotelData

@Dao
interface UserHotelDao {
    //Get all hotels booked by user
    @Query("SELECT * FROM user_hotels_table")
    fun getAllBookedHotels(): LiveData<List<UserHotelData>>

    //Add new booked hotel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookedHotel(userHotelData: UserHotelData)

    //Remove a booked hotel
    @Delete
    fun removeBookedHotel(userHotelData: UserHotelData)
}