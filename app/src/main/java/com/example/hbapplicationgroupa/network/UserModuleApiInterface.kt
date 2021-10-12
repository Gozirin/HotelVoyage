package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserModuleApiInterface {
    @GET("api/v1/Users/get-user/{userId}")
    suspend fun getUserById(
        @Path("id") id: String
    ): Response<GetUserByIdResponseModel>

    @PATCH("api/v1/Users/update-user/{userId}/user-photo")
    suspend fun updateUserPhotoById(
        @Path("id") id: String,
        @Body Photo: Int
    ): Response<UpdateUserPhotoByUserIdResponseModel>
}