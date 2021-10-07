package com.example.hbapplicationgroupa.repository.hotelDaoRepository

import androidx.lifecycle.LiveData
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
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelDaoRepositoryInterface {

    //Get all hotels
    fun getAllHotels(): LiveData<List<HotelData>>


    suspend fun getHotelById(hotelId: String, pageSize: Int): Response<Hotel>

    suspend fun getTopHotels(pageSize: Int, pageNumber: Int, ): Response<List<TopHotels>>

    suspend fun getTopDeals(pageSize: Int, pageNumber: Int):Response<List<TopDeals>>

    suspend fun getAllHotels(id: String): Response<List<AllHotels>>

    suspend fun getHotelRoomsByPrice(id: String, pageSize: Int, pageNumber: Int, isBooked: Boolean, price: Int): Response<List<HotelRoomsByPrice>>

    suspend fun getHotelRoomsByAvailability(pageSize: Int, pageNumber: Int, isBooked: Boolean): Response<List<HotelRoomsVacancy>>

    suspend fun getHotelRoomById(roomId: String): Response<HotelRoomById>

    suspend fun getHotelRatings(hotelId: String): Response<List<HotelRatings>>

    suspend fun getHotelStatistics(hotelId: String): Response<HotelStatistics>







}