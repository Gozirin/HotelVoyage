package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData
import com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel.HotelAmenities
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface AmenitiesDaoRepositoryInterface {




    suspend fun getAmenitiesById(id: String): LiveData<List<AmenitiesData>>

    fun addAmenities(amenitiesData: AmenitiesData)


    suspend fun getHotelAmenities(hotelId: String): Response<HotelAmenities>



}