package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.HotelDao
import com.example.hbapplicationgroupa.models.hotelModule.allHotelsModel.AllHotels
import com.example.hbapplicationgroupa.models.hotelModule.hotelModel.Hotel
import com.example.hbapplicationgroupa.models.hotelModule.hotelModel.HotelData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatings
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel.HotelRoomById
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsByPriceModel.HotelRoomsByPrice
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsVacancyModel.HotelRoomsVacancy
import com.example.hbapplicationgroupa.models.hotelModule.hotelStatisticsModel.HotelStatistics
import com.example.hbapplicationgroupa.models.hotelModule.topDealsModel.TopDeals
import com.example.hbapplicationgroupa.models.hotelModule.topHotelsModel.TopHotels
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBCustomerModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBHotelModuleApi
import retrofit2.Response
import javax.inject.Inject

class HotelDaoRepository @Inject constructor(val hotelModuleApi: HBHotelModuleApi, val hotelDao: HotelDao): HotelDaoRepositoryInterface{

    override fun getAllHotels(): LiveData<List<HotelData>> {
      return hotelDao.getAllHotels()
    }

    override suspend fun getAllHotels(id: String): Response<List<AllHotels>> {
      return hotelModuleApi.getAllHotels(id)
    }

    override suspend fun getHotelById(hotelId: String, pageSize: Int): Response<Hotel> {
       return hotelModuleApi.getHotelById(hotelId, pageSize)
    }

    override suspend fun getTopHotels(pageSize: Int, pageNumber: Int): Response<List<TopHotels>> {
        return hotelModuleApi.getTopHotels(pageSize,pageNumber)
    }

    override suspend fun getTopDeals(pageSize: Int, pageNumber: Int): Response<List<TopDeals>> {
      return hotelModuleApi.getTopDeals(pageSize, pageNumber)
    }

    override suspend fun getHotelRoomsByPrice(
        id: String,
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean,
        price: Int
    ): Response<List<HotelRoomsByPrice>> {
        return hotelModuleApi.getHotelRoomsByPrice(id,pageSize,pageNumber,isBooked,price)
    }

    override suspend fun getHotelRoomsByAvailability(
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean
    ): Response<List<HotelRoomsVacancy>> {
       return hotelModuleApi.getHotelRoomsByAvailability(pageSize,pageNumber,isBooked)
    }

    override suspend fun getHotelRoomById(roomId: String): Response<HotelRoomById> {
        return hotelModuleApi.getHotelRoomById(roomId)
    }

    override suspend fun getHotelRatings(hotelId: String): Response<List<HotelRatings>> {
       return hotelModuleApi.getHotelRatings(hotelId)
    }

    override suspend fun getHotelStatistics(hotelId: String): Response<HotelStatistics> {
        return hotelModuleApi.getHotelStatistics(hotelId)
    }
}