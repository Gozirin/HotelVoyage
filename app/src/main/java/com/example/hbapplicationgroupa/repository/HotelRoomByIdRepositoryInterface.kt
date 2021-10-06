package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserByIdData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel.HotelRoomByIdData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface HotelRoomByIdRepositoryInterface {
    //Get all hotel rooms using room id
    fun getHotelRoomById(roomId: String): LiveData<List<HotelRoomByIdData>>

    //Add hotel rooms by id
    fun addHotelRoomById(hotelRoomByIdData: HotelRoomByIdData)


    suspend fun addUserData(userByIdData: UserByIdData): Response<UserById>


    suspend fun getUserLogin(EmailAddress: String, Password: String): Response<UserById>



    suspend fun updateUserPassword(oldPassword: String, newPassword: String): Response<UserById>



    suspend fun verifyForgotPassword(EmailAddress: String): Response<UserById>


    suspend fun resetPassword(EmailAddress: String, Token: String, Password: String): Response<UserById>


    suspend fun confirmEmailAddress(EmailAddress: String, Token: String): Response<UserById>


}