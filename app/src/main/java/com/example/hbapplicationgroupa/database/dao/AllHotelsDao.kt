package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.model.hotelmodule.getallhotels.GetAllHotelsResponseItem


interface AllHotelsDao {
    //Add all hotel to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotel: GetAllHotelsResponseItem)


    //Get all hotel from database
    @Query("SELECT * FROM getAllHotel")
    fun getAllHotel(): LiveData<List<GetAllHotelsResponseItem>>

}