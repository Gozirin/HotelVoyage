package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface AmenitiesRepositoryInterface {

    suspend fun ToGetAmenitiesById(id: String): LiveData<List<AmenitiesData>>

    fun ToaddAmenities(amenitiesData: AmenitiesData)

    suspend fun ToUpdateUserById(userId: String): Response<UserById>

    suspend fun ToGetUserById(@Query("userId") Id: String): Response<UserById>

    suspend fun ToUpdateUserPhotoByUserId(userId: String, image: MultipartBody.Part): Response<UserById>


}