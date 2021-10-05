package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel.HotelRoomByIdData

interface HotelRoomByIdDao {
    //Get all hotel rooms using room id
    @Query("SELECT * FROM hotelRoom_by_id_table WHERE id LIKE :roomId")
    fun getHotelRoomById(roomId: String): LiveData<List<HotelRoomByIdData>>

    //Add hotel rooms by id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHotelRoomById(hotelRoomByIdData: HotelRoomByIdData)
}