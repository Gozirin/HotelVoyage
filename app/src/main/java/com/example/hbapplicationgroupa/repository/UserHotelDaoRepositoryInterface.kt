package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel.UserHotelData

interface UserHotelDaoRepositoryInterface {

    //Get all hotels booked by user
    fun getAllBookedHotels(): LiveData<List<UserHotelData>>

    //Add new booked hotel
    fun addBookedHotel(userHotelData: UserHotelData)

    //Remove a booked hotel
    fun removeBookedHotel(userHotelData: UserHotelData)
}