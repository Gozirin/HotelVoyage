package com.example.hbapplicationgroupa.repository.usermodulerepository

import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path

interface UserRepositoryInterface {
    suspend fun getUserById(token: String): Response<GetUserByIdResponseModel>
    suspend fun updateUserPhotoById(id: String, Photo: Int): Response<UpdateUserPhotoByUserIdResponseModel>
}