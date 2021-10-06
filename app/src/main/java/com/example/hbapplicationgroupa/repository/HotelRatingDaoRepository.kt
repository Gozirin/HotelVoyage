package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.HotelRatingsDao
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatingsData
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAdminModuleApi
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class HotelRatingDaoRepository @Inject constructor(val hbAdminModuleApi: HBAdminModuleApi, val hotelRatingsDao: HotelRatingsDao): HotelRatingDaoRepositoryInterface {

    override fun getRatingsByHotelId(hotelId: String): LiveData<List<HotelRatingsData>> {
        return hotelRatingsDao.getRatingsByHotelId(hotelId)
    }

    override fun addHotelRating(hotelRatingsData: HotelRatingsData) {
            hotelRatingsDao.addHotelRating(hotelRatingsData)
    }

    override suspend fun getUsersByPage(
        pageSize: Int,
        pageNumber: Int
    ): Response<List<UsersByPageNumber>> {
       return hbAdminModuleApi.getUsersByPage(pageSize, pageNumber)
    }

    override suspend fun updateUserById(userId: String): Response<UserById> {
        return hbAdminModuleApi.updateUserById(userId)
    }

    override suspend fun getUserById(Id: String): Response<UserById> {
        return hbAdminModuleApi.getUserById(Id)
    }

    override suspend fun updateUserPhotoByUserId(
        userId: String,
        image: MultipartBody.Part
    ): Response<UserById> {
        return hbAdminModuleApi.updateUserPhotoByUserId(userId, image)
    }
}