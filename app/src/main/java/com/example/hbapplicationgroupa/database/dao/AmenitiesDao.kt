package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData

@Dao
interface AmenitiesDao {
    //Get all amenities using hotel id
    @Query("SELECT * FROM amenities_table WHERE id LIKE :id")
    fun getAmenitiesById(id: String): LiveData<List<AmenitiesData>>

    //Add amenities to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAmenities(amenitiesData: AmenitiesData)
}