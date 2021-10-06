package com.example.hbapplicationgroupa.repository


import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.AmenitiesDao
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAdminModuleApi
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class AmenitiesDaoRepository @Inject constructor(val api: HBAdminModuleApi, val responseDao: AmenitiesDao): AmenitiesRepositoryInterface {
    override suspend fun ToGetAmenitiesById(id: String): LiveData<List<AmenitiesData>> {
        return responseDao.getAmenitiesById(id)
    }

    override fun ToaddAmenities(amenitiesData: AmenitiesData) {
       responseDao.addAmenities(amenitiesData)
    }

    override suspend fun ToUpdateUserById(userId: String): Response<UserById> {
       return api.updateUserById(userId)
    }

    override suspend fun ToGetUserById(Id: String): Response<UserById> {
       return api.getUserById(Id)
    }

    override suspend fun ToUpdateUserPhotoByUserId(userId: String, image: MultipartBody.Part): Response<UserById> {
      return api.updateUserPhotoByUserId(userId,image)

    }

}