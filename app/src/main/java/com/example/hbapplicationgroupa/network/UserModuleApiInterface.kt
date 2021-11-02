package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseModel
import retrofit2.Response
import retrofit2.http.*

interface UserModuleApiInterface {
    @GET("api/Customer")
    suspend fun getUserById(
        @Header("Authorization")
        token: String
    ): Response<GetUserByIdResponseModel>

    @PATCH("api/v1/Users/update-user/{userId}/user-photo")
    suspend fun updateUserPhotoById(
        @Path("id") id: String,
        @Body Photo: Int
    ): Response<UpdateUserPhotoByUserIdResponseModel>
}