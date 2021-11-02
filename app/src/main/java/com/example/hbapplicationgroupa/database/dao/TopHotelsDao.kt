package com.example.hbapplicationgroupa.database.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem


@Dao
interface TopHotelsDao {

   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTopHotels(topHotels: GetTopHotelsResponseItem): List<GetTopHotelsResponseItem>


  //  @Query("SELECT * FROM topHotels")
    fun getTopHotels(): LiveData<List<GetTopHotelsResponseItem>>
}