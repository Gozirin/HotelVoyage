package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatingsData
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface HotelRatingDaoRepositoryInterface {

    //Get ratings by hotel id
    fun getRatingsByHotelId(hotelId: String): LiveData<List<HotelRatingsData>>

    //Add a rating
    fun addHotelRating(hotelRatingsData: HotelRatingsData)

    suspend fun getUsersByPage(pageSize: Int, pageNumber: Int): Response<List<UsersByPageNumber>>


    suspend fun updateUserById(userId: String): Response<UserById>


    @GET("Users/get-user/{userId}")
    suspend fun getUserById(Id: String): Response<UserById>


    suspend fun updateUserPhotoByUserId(userId: String, image: MultipartBody.Part): Response<UserById>

}