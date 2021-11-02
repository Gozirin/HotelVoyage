package com.example.hbapplicationgroupa.repository.usermodulerepository

import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseModel
import com.example.hbapplicationgroupa.network.UserModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userModuleApiInterface: UserModuleApiInterface): UserRepositoryInterface {
    override suspend fun getUserById(token: String): Response<GetUserByIdResponseModel> {
        return userModuleApiInterface.getUserById(token)
    }

    override suspend fun updateUserPhotoById(
        id: String,
        Photo: Int
    ): Response<UpdateUserPhotoByUserIdResponseModel> {
        return userModuleApiInterface.updateUserPhotoById(id, Photo)
    }
}