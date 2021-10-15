package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData

@Dao
interface HotelByIdDao {
    @Query("SELECT * FROM getHotelById")
    fun getHotelById(): LiveData<List<GetHotelByIdResponseItemData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotel: GetHotelByIdResponseItemData)

    @Delete
    suspend fun removeHotel(hotel: GetHotelByIdResponseItemData)
}