package com.example.hbapplicationgroupa.network.hotelbookingapi

import com.example.hbapplicationgroupa.models.hotelModule.allHotelsModel.AllHotels
import com.example.hbapplicationgroupa.models.hotelModule.hotelModel.Hotel
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatings
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel.HotelRoomById
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsByPriceModel.HotelRoomsByPrice
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsVacancyModel.HotelRoomsVacancy
import com.example.hbapplicationgroupa.models.hotelModule.hotelStatisticsModel.HotelStatistics
import com.example.hbapplicationgroupa.models.hotelModule.topDealsModel.TopDeals
import com.example.hbapplicationgroupa.models.hotelModule.topHotelsModel.TopHotels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HBHotelModuleApi {

    @GET("Hotel/{hotelId}")
    suspend fun getHotelById(
        @Path("hotelId") hotelId: String,
        @Query("pageSize") pageSize: Int
    ): Response<Hotel>


    @POST("Hotel/top-hotels")
    suspend fun getTopHotels(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
    ): Response<List<TopHotels>>


    @GET("Hotel/top-deals")
    suspend fun getTopDeals(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int
    ):Response<List<TopDeals>>


    @GET("Hotels/{Id}/")
    suspend fun getAllHotels(@Path("id") id: String): Response<List<AllHotels>>


    @GET("Hotels/{Id}/rooms")
    suspend fun getHotelRoomsByPrice(
        @Path("id") id: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
        @Query("price") price: Int
    ): Response<List<HotelRoomsByPrice>>


    @GET("")
    suspend fun getHotelRoomsByAvailability(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
    ): Response<List<HotelRoomsVacancy>>


    @GET("Hotel/room/{roomId}")
    suspend fun getHotelRoomById(@Path("roomId") roomId: String): Response<HotelRoomById>


    @GET("Hotel/{hotelId}/ratings")
    suspend fun getHotelRatings(@Path("hotelId") hotelId: String): Response<List<HotelRatings>>


    @GET("Hotel/{hotelId}/statistics")
    suspend fun getHotelStatistics(@Path("hotelId") hotelId: String): Response<HotelStatistics>

}