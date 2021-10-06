package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.UserHotelDao
import com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel.UserHotelData
import javax.inject.Inject

class UserHotelDaoRepository @Inject constructor(val userHotelDao: UserHotelDao): UserHotelDaoRepositoryInterface {
    override fun getAllBookedHotels(): LiveData<List<UserHotelData>> {
        return userHotelDao.getAllBookedHotels()
    }

    override fun addBookedHotel(userHotelData: UserHotelData) {
         userHotelDao.addBookedHotel(userHotelData)
    }

    override fun removeBookedHotel(userHotelData: UserHotelData) {
        userHotelDao.removeBookedHotel(userHotelData)
    }


}