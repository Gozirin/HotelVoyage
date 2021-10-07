package com.example.hbapplicationgroupa.repository.hotelRoomByIdDaoRepository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.HotelRoomByIdDao
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserById
import com.example.hbapplicationgroupa.models.adminModule.userByIdModel.UserByIdData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel.HotelRoomByIdData
import com.example.hbapplicationgroupa.network.HBAuthenticationModuleApi
import com.example.hbapplicationgroupa.repository.hotelRoomByIdDaoRepository.HotelRoomByIdRepositoryInterface
import retrofit2.Response
import javax.inject.Inject

class HotelRoomDaoByIdRepository @Inject constructor(val hbAuthenticationModule: HBAuthenticationModuleApi, val hotelRoomByIdDao: HotelRoomByIdDao):
    HotelRoomByIdRepositoryInterface {
    override fun getHotelRoomById(roomId: String): LiveData<List<HotelRoomByIdData>> {
        return hotelRoomByIdDao.getHotelRoomById(roomId)
    }

    override fun addHotelRoomById(hotelRoomByIdData: HotelRoomByIdData) {
       hotelRoomByIdDao.addHotelRoomById(hotelRoomByIdData)
    }

    override suspend fun addUserData(userByIdData: UserByIdData): Response<UserById> {
      return hbAuthenticationModule.addUserData(userByIdData)
    }

    override suspend fun getUserLogin(EmailAddress: String, Password: String): Response<UserById> {
      return hbAuthenticationModule.getUserLogin(EmailAddress,Password)
    }

    override suspend fun updateUserPassword(
        oldPassword: String,
        newPassword: String
    ): Response<UserById> {
        return hbAuthenticationModule.updateUserPassword(oldPassword,newPassword)
    }

    override suspend fun verifyForgotPassword(EmailAddress: String): Response<UserById> {
        return hbAuthenticationModule.verifyForgotPassword(EmailAddress)
    }

    override suspend fun resetPassword(
        EmailAddress: String,
        Token: String,
        Password: String
    ): Response<UserById> {
       return hbAuthenticationModule.resetPassword(EmailAddress, Token, Password)
    }

    override suspend fun confirmEmailAddress(
        EmailAddress: String,
        Token: String
    ): Response<UserById> {
       return hbAuthenticationModule.confirmEmailAddress(EmailAddress, Token)
    }
}