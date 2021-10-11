package com.example.hbapplicationgroupa.repos.usermodulerepository

import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path

interface UserRepositoryInterface {
    suspend fun getUserById(
        @Path("id") id: String
    ): Response<GetUserByIdResponseModel>

    suspend fun updateUserPhotoById(
        @Path("id") id: String,
        @Body Photo: Int
    ): Response<UpdateUserPhotoByUserIdResponseModel>
}